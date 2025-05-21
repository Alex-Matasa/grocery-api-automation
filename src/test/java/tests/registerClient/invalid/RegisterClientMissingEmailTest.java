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

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RegisterClientMissingEmailTest {

    @Test
            public void registerClientNoEmail() {

        User user = JsonDeserializer.fromFile(TestDataPaths.REGISTER_CLIENT_MISSING_EMAIL_JSON, User.class);

        Response response = given()
                .spec(BaseRequestSpec.withoutAuth())
                .body(user)
                .post(EndpointPaths.REGISTER_CLIENT);

        assertThat(response.getStatusCode(), equalTo(400));
        assertThat(response.jsonPath().getString(ResponseFields.ERROR), equalTo(ExpectedMessages.REGISTER_CLIENT_MISSING_EMAIL_MESSAGE));
    }
}
