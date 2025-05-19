package tests;

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

public class CreateUserTest {

    @Test
            public void createUser() {

        User user = JsonDeserializer.fromFile(TestDataPaths.USER_JSON, User.class);
        user.setClientEmail("automation+" + System.currentTimeMillis() + "@example.com");


        Response response = given()
                .spec(BaseRequestSpec.withoutAuth())
                .body(user)
                .post(EndpointPaths.REGISTER_CLIENT);

        assertThat(response.getStatusCode(), equalTo(201));

        TokenManager.setToken(response.jsonPath().getString("accessToken"));
    }

}
