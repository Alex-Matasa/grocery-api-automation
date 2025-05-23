package utils;

import io.restassured.response.Response;
import logger.LoggerUtility;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AssertionUtils {

    public static void assertStatusCode(Response response, int expectedStatusCode) {
        assertThat(response.getStatusCode(), equalTo(expectedStatusCode));
        LoggerUtility.info("Validated Status code: " + expectedStatusCode);
    }

    public static void assertErrorMessage(Response response, String expectedMessage) {
        String actualMessage = response.jsonPath().getString(ResponseFields.ERROR);
        assertThat(actualMessage, equalTo(expectedMessage));
        LoggerUtility.info("Validated Error message");
    }
}
