package ch.zhaw.oop.stocks.api;

import ch.zhaw.oop.stocks.api.pojo.SimpleTestCaseJsonPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    // ADR: Source is a Json String
    private static final String simpleTestCaseJsonSource = "{\n" +
            "  \"title\": \"This is a title\",\n" +
            "  \"author\": \"Peter Lustig\"\n" +
            "}";
    private static JsonNode node;

    @BeforeAll
    static void setUp() throws JsonProcessingException {
        node = Json.parse(simpleTestCaseJsonSource);
    }

    // ADR: Testing the conversion from Json String to JsonNode class
    @Test
    void parse() {
        assertEquals(node.get("title").asText(), "This is a title");
    }

    // ADR: Testing the mapping from JsonNode class to a Java class
    @Test
    void fromJson() throws JsonProcessingException {
        SimpleTestCaseJsonPOJO json = Json.fromJson(node, SimpleTestCaseJsonPOJO.class);
        assertEquals(json.getTitle(), "This is a title");
    }
}