package tests.registerClient.valid;

import data.dataProviders.RegisterClientDataProvider;
import data.request.User;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import services.ClientService;
import suites.TestSuite;
import utils.AssertionUtils;
import utils.ExpectedMessages;
import utils.ResponseFields;
import utils.TokenManager;

public class RegisterClientValidNameFormatsTest {

    @Test(
            dataProvider = "validNameFormats",
            dataProviderClass = RegisterClientDataProvider.class,
            groups = {TestSuite.REGISTER_CLIENT, TestSuite.VALID_REGISTER_CLIENT}
    )

    public void registerClientValidNameFormats(User user) {

        user.setClientEmail(System.currentTimeMillis() + user.getClientEmail());

        Response response = ClientService.registerClient(user);

        AssertionUtils.assertStatusCode(response, 201);
        TokenManager.setToken(response.jsonPath().getString(ResponseFields.ACCESS_TOKEN));
    }
}
