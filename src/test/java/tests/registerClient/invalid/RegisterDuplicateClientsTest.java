package tests.registerClient.invalid;

import base.BaseRequestSpec;
import base.JsonDeserializer;
import base.ResponseFields;
import base.TestDataPaths;
import endpoints.EndpointPaths;
import io.restassured.response.Response;
import models.request.User;
import org.testng.annotations.Test;
import utils.ExpectedMessages;
import utils.TokenManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RegisterDuplicateClientsTest {

    @Test
            public void registerClient() {

        User user = JsonDeserializer.fromFile(TestDataPaths.REGISTER_CLIENT_JSON, User.class);
        user.setClientEmail(System.currentTimeMillis() + user.getClientEmail());

        Response firstResponse = given()
                .spec(BaseRequestSpec.withoutAuth())
                .body(user)
                .post(EndpointPaths.REGISTER_CLIENT);

        assertThat(firstResponse.getStatusCode(), equalTo(201));

        TokenManager.setToken(firstResponse.jsonPath().getString("accessToken"));

        Response secondResponse = given()
                .spec(BaseRequestSpec.withoutAuth())
                .body(user)
                .post(EndpointPaths.REGISTER_CLIENT);

        assertThat(secondResponse.getStatusCode(), equalTo(409));
        assertThat(secondResponse.jsonPath().getString(ResponseFields.ERROR), equalTo(ExpectedMessages.REGISTER_DUPLICATE_CLIENTS_MESSAGE));
    }
}
