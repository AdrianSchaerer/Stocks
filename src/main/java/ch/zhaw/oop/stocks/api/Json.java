package ch.zhaw.oop.stocks.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 *  <h1>Json</h1>
 *  The Json class is used to parse Json to Java code using an ObjectMapper.
 * @author      Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version     1.0
 * @since       2023-06-23
 */
public class Json {
    private static final ObjectMapper objectMapper = getDefaultObjectMapper();
    /**
     * <p>The class method "getDefaultObjectMapper" creates a new instance of the ObjectMapper.</p>
     * <p>This part of the application is not managed by Spring.</p>
     * <p>ObjectMapper ist used to serialize Java objects into JSON and deserialize JSON string into Java objects.</p>
     * <p>The class method prevents an error if the Json doesn't match the requirements needed.</p>
     * @return ObjectMapper
     */
    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.registerModule(new JavaTimeModule());
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    /**
     * @param src Json
     * @return creating a Node from JSON String
     * @throws JsonProcessingException
     */
    public static JsonNode parse(String src) throws JsonProcessingException {
        return objectMapper.readTree(src);
    }

    /**
     * @param node JsonNode
     * @param clazz variable
     * @param <A> there can be different classes to be mapped to
     * @return converting Node to Java Object
     * @throws JsonProcessingException
     */
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }
}
