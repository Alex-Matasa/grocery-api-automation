package data.dataProviders;


import base.JsonDeserializer;
import data.DataModel;
import org.testng.annotations.DataProvider;
import utils.TestDataPaths;

public class RegisterClientDataProvider {

    @DataProvider(name = "invalidEmailFormats")
    public static Object[][] provideUsersWithInvalidEmails() {
        DataModel model = JsonDeserializer.fromFile(
                TestDataPaths.REGISTER_CLIENT_INVALID_EMAIL_FORMAT_JSON,
                DataModel.class
        );

        return model.getUsers()
                .stream()
                .map(user -> new Object[]{user})
                .toArray(Object[][]::new);
    }
}
