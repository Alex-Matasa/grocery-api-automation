package tests.registerClient.invalid;

import base.JsonDeserializer;
import data.DataModel;
import suites.TestSuite;
import utils.TestDataPaths;
import io.restassured.response.Response;
import data.request.User;
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

        User user = JsonDeserializer.fromFile(TestDataPaths.REGISTER_CLIENT_JSON, DataModel.class).getUsers().get(0);
        user.setClientEmail(System.currentTimeMillis() + user.getClientEmail());

        Response firstResponse = ClientService.registerClient(user);

        AssertionUtils.assertStatusCode(firstResponse, 201);

        TokenManager.setToken(firstResponse.jsonPath().getString("accessToken"));

        Response secondResponse = ClientService.registerClient(user);

        AssertionUtils.assertStatusCode(secondResponse, 409);
        AssertionUtils.assertErrorMessage(secondResponse, ExpectedMessages.REGISTER_DUPLICATE_CLIENTS_MESSAGE);
    }
}
