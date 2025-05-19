package utils;

import lombok.Setter;

public class TokenManager {

    @Setter
    private static String token;

    public static String getToken() {
        if (token == null || token.isEmpty()) {
            throw new IllegalStateException("Token has not been set. Did you forget to register the client?");
        }
        return token;
    }
}
