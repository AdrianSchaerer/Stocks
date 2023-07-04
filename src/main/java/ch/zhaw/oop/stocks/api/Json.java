package ch.zhaw.oop.stocks.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * <p>The Json class is used to parse Json to Java code using an ObjectMapper.</p>
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
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
     * Parse json node.
     *
     * @param src Json
     * @return creating a Node from JSON String
     * @throws JsonProcessingException the json processing exception
     */
    public static JsonNode parse(String src) throws JsonProcessingException {
        return objectMapper.readTree(src);
    }

    /**
     * From json a.
     *
     * @param <A>   there can be different classes to be mapped to
     * @param node  JsonNode
     * @param clazz variable
     * @return converting Node to Java Object
     * @throws JsonProcessingException the json processing exception
     */
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }
}
