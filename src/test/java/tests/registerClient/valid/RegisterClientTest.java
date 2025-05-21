package tests.registerClient.valid;

import base.BaseRequestSpec;
import base.JsonDeserializer;
import base.TestDataPaths;
import models.request.User;
import endpoints.EndpointPaths;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TokenManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RegisterClientTest {

    @Test
            public void registerClient() {

        User user = JsonDeserializer.fromFile(TestDataPaths.REGISTER_CLIENT_JSON, User.class);
        user.setClientEmail(System.currentTimeMillis() + user.getClientEmail());

        Response response = given()
                .spec(BaseRequestSpec.withoutAuth())
                .body(user)
                .post(EndpointPaths.REGISTER_CLIENT);

        assertThat(response.getStatusCode(), equalTo(201));

        TokenManager.setToken(response.jsonPath().getString("accessToken"));
    }
}
