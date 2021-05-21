package org.example.testtask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class JsonConverter {

    private final ObjectMapper mapper = new ObjectMapper();

    public String toJson(Object object) {
        try {
            return toJsonWithException(object);
        } catch (JsonProcessingException e){
            e.printStackTrace();

            return null;
        }
    }

    /**
     * Convert json string into java object
     *
     * @param json string to be converted
     * @param requiredType result type of converting json string
     * @param <T> result class of converting json string
     * @return result of converting json string into java object
     */
    public <T> Optional<T> fromJson(String json, Class<T> requiredType) throws IOException {
        try {
            return Optional.of(fromJsonWithException(json, requiredType));
        } catch (JsonProcessingException e){
            e.printStackTrace();

            return Optional.empty();
        }
    }

    /**
     * Convert to json with exception (thrown if an error occurred)
     *
     * @param object that will be converted
     * @return result of converting (json string)
     * @throws JsonProcessingException error while converting
     */
    private String toJsonWithException(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    /**
     * Convert from json to java object with exception (thrown if an error occurred)
     *
     * @param json that will be converted
     * @param requiredType result type of converting json string
     * @param <T> result class of converting json string
     * @return result of converting json string into java object
     * @throws JsonProcessingException error while converting
     */
    private <T> T fromJsonWithException(String json, Class<T> requiredType) throws IOException {
        return mapper.readValue(json, requiredType);
    }
}
