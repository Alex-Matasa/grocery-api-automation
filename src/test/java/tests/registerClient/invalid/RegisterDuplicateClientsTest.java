package tests.registerClient.invalid;

import base.JsonDeserializer;
import suites.TestSuite;
import utils.TestDataPaths;
import io.restassured.response.Response;
import models.request.User;
import org.testng.annotations.Test;
import services.ClientService;
import utils.AssertionUtils;
import utils.ExpectedMessages;
import utils.TokenManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegisterDuplicateClientsTest {

    @Test(groups = {
            TestSuite.REGRESSION,
            TestSuite.REGISTER_CLIENT,
            TestSuite.INVALID_REGISTER_CLIENT
    })

    public void registerClient() {

        User user = JsonDeserializer.fromFile(TestDataPaths.REGISTER_CLIENT_JSON, User.class);
        user.setClientEmail(System.currentTimeMillis() + user.getClientEmail());

        Response firstResponse = ClientService.registerClient(user);

        AssertionUtils.assertStatusCode(firstResponse, 201);

        TokenManager.setToken(firstResponse.jsonPath().getString("accessToken"));

        Response secondResponse = ClientService.registerClient(user);

        AssertionUtils.assertStatusCode(secondResponse, 409);
        AssertionUtils.assertErrorMessage(secondResponse, ExpectedMessages.REGISTER_DUPLICATE_CLIENTS_MESSAGE);
    }
}
