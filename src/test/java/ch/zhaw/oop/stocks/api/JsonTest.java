package ch.zhaw.oop.stocks.api;

import ch.zhaw.oop.stocks.pojo.SimpleTestCaseJsonPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String simpleTestCaseJsonSource = "{\n" +
            "  \"title\": \"This is a title\",\n" +
            "  \"author\": \"Peter Lustig\"\n" +
            "}";

    @Test
    void parse() throws JsonProcessingException {
        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        assertEquals(node.get("title").asText(), "This is a title");
    }

    @Test
    void fromJson() throws JsonProcessingException {
        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        SimpleTestCaseJsonPOJO pojo = Json.fromJson(node, SimpleTestCaseJsonPOJO.class);
        assertEquals(pojo.getTitle(), "This is a title");
    }
}