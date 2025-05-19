package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseRequestSpec {

    public static RequestSpecification withAuth(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(EnvironmentManager.getBaseUrl())
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("Content-Type", "application/json")
                .build();
    }

    public static RequestSpecification withoutAuth() {
        return new RequestSpecBuilder()
                .setBaseUri(EnvironmentManager.getBaseUrl())
                .addHeader("Content-Type", "application/json")
                .build();
    }
}
