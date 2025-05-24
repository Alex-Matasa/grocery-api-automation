package services;

import base.BaseRequestSpec;
import endpoints.EndpointPaths;
import io.restassured.response.Response;
import logger.LoggerUtility;
import data.request.User;

import static io.restassured.RestAssured.given;

public class ClientService {

    public static Response registerClient(User user) {
        LoggerUtility.info("Registering client with email: " + user.getClientEmail());

        Response response = given()
                .spec(BaseRequestSpec.withoutAuth())
                .body(user)
                .post(EndpointPaths.REGISTER_CLIENT);

        LoggerUtility.info("Received response: Status " + response.getStatusCode());
        LoggerUtility.info("Response body: " + response.asPrettyString());

        return response;
    }
}
