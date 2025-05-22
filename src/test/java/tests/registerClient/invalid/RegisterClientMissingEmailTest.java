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

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegisterClientMissingEmailTest {

    @Test(groups = {
            TestSuite.REGRESSION,
            TestSuite.REGISTER_CLIENT,
            TestSuite.INVALID_REGISTER_CLIENT
    })

    public void registerClientNoEmail() {

        User user = JsonDeserializer.fromFile(TestDataPaths.REGISTER_CLIENT_MISSING_EMAIL_JSON, User.class);

        Response response = ClientService.registerClient(user);

        AssertionUtils.assertStatusCode(response, 400);
        AssertionUtils.assertErrorMessage(response, ExpectedMessages.REGISTER_CLIENT_MISSING_EMAIL_MESSAGE);
    }
}
