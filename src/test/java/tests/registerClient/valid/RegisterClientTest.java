package tests.registerClient.valid;

import base.JsonDeserializer;
import data.DataModel;
import suites.TestSuite;
import utils.ResponseFields;
import utils.TestDataPaths;
import data.request.User;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import services.ClientService;
import utils.AssertionUtils;
import utils.TokenManager;


public class RegisterClientTest {

    @Test(groups = {
            TestSuite.REGRESSION,
            TestSuite.REGISTER_CLIENT,
            TestSuite.VALID_REGISTER_CLIENT
    })

    public void registerClient() {

        User user = JsonDeserializer.fromFile(TestDataPaths.REGISTER_CLIENT_JSON, DataModel.class).getUsers().get(0);
        user.setClientEmail(System.currentTimeMillis() + user.getClientEmail());

        Response response = ClientService.registerClient(user);

        AssertionUtils.assertStatusCode(response, 201);

        TokenManager.setToken(response.jsonPath().getString(ResponseFields.ACCESS_TOKEN));
    }
}
