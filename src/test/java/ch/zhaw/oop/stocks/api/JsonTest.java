package ch.zhaw.oop.stocks.api;

import ch.zhaw.oop.stocks.api.pojo.JsonPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    // ADR: Source is a Json String
    private final String simpleTestCaseJsonSource = "{\n" +
            "  \"title\": \"This is a title\",\n" +
            "  \"author\": \"Peter Lustig\"\n" +
            "}";

    // ADR: Testing the conversion from Json String to JsonNode class
    @Test
    void parse() throws JsonProcessingException {
        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        assertEquals(node.get("title").asText(), "This is a title");
    }

    // ADR: Testing the mapping from JsonNode class to a Java class
    @Test
    void fromJson() throws JsonProcessingException {
        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        JsonPOJO pojo = Json.fromJson(node, JsonPOJO.class);
        assertEquals(pojo.getTitle(), "This is a title");
    }
}