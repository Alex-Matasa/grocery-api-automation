package base;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonDeserializer {
    public static <T> T fromFile(String path, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(path), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON from: " + path, e);
        }
    }
}
