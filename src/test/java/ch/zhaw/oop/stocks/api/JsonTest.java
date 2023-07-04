package ch.zhaw.oop.stocks.api;

import ch.zhaw.oop.stocks.api.pojo.JsonPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Json test.
 */
class JsonTest {

    // ADR: Source is a Json String
    private static final String simpleTestCaseJsonSource = "{\n" +
            "  \"title\": \"This is a title\",\n" +
            "  \"author\": \"Peter Lustig\"\n" +
            "}";
    private static JsonNode node;

    /**
     * Sets up.
     *
     * @throws JsonProcessingException the json processing exception
     */
    @BeforeAll
    static void setUp() throws JsonProcessingException {
        node = Json.parse(simpleTestCaseJsonSource);
    }

    /**
     * Parse.
     *
     * @throws JsonProcessingException the json processing exception
     */
// ADR: Testing the conversion from Json String to JsonNode class
    @Test
    void parse() throws JsonProcessingException {
        assertEquals(node.get("title").asText(), "This is a title");
    }

    /**
     * From json.
     *
     * @throws JsonProcessingException the json processing exception
     */
// ADR: Testing the mapping from JsonNode class to a Java class
    @Test
    void fromJson() throws JsonProcessingException {
        JsonPOJO pojo = Json.fromJson(node, JsonPOJO.class);
        assertEquals(pojo.getTitle(), "This is a title");
    }
}