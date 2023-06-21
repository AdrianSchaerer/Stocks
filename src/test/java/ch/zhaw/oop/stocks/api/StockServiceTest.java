package ch.zhaw.oop.stocks.api;

import ch.zhaw.oop.stocks.pojo.EarliestTimestampPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockServiceTest {

    private String earliestTimeStampSource = "{\n" +
            "  \"datetime\": \"1970-01-01\",\n" +
            "  \"unix_time\": 52200\n" +
            "}";

    private String stocksSource = "{\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"symbol\": \"AACI\",\n" +
            "      \"name\": \"Armada Acquisition Corp. I\",\n" +
            "      \"currency\": \"USD\",\n" +
            "      \"exchange\": \"NASDAQ\",\n" +
            "      \"mic_code\": \"XNMS\",\n" +
            "      \"country\": \"United States\",\n" +
            "      \"type\": \"Common Stock\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"ok\"\n" +
            "}";

    @Test
    void stocks() throws JsonProcessingException {
        JsonNode node = Json.parse(stocksSource);
        StockDescriptionList pojo = Json.fromJson(node, StockDescriptionList.class);
        // how to test??
        //assertEquals("data", pojo.getData().contains("data"));

    }

    @Test
    void earliest_timestamp() throws JsonProcessingException {
        JsonNode node = Json.parse(earliestTimeStampSource);
        EarliestTimestampPOJO pojo = Json.fromJson(node, EarliestTimestampPOJO.class);
        assertEquals("1970-01-01", pojo.getDatetime().toString());
    }

    @Test
    void time_series() {
        // ToDo
    }
}