package services;

import base.BaseRequestSpec;
import endpoints.EndpointPaths;
import io.restassured.response.Response;
import models.request.User;

import static io.restassured.RestAssured.given;

public class ClientService {

    public static Response registerClient(User user) {
        return given()
                .spec(BaseRequestSpec.withoutAuth())
                .body(user)
                .post(EndpointPaths.REGISTER_CLIENT);
    }
}
