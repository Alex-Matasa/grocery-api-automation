package tests.registerClient.invalid;

import data.dataProviders.RegisterClientDataProvider;
import data.request.User;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import services.ClientService;
import suites.TestSuite;
import utils.AssertionUtils;
import utils.ExpectedMessages;

public class RegisterClientInvalidEmailTest {

    @Test(
            dataProvider = "invalidEmailFormats",
            dataProviderClass = RegisterClientDataProvider.class,
            groups = {TestSuite.REGRESSION, TestSuite.REGISTER_CLIENT, TestSuite.INVALID_REGISTER_CLIENT}
    )

    public void registerClientInvalidEmail(User user) {

        Response response = ClientService.registerClient(user);

        AssertionUtils.assertStatusCode(response, 400);
        AssertionUtils.assertErrorMessage(response, ExpectedMessages.REGISTER_CLIENT_INVALID_EMAIL_MESSAGE);
    }
}
