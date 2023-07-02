package ch.zhaw.oop.stocks.api;

import ch.zhaw.oop.stocks.api.pojo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StockServiceTest {

    private final String earliestTimeStampSource = "{\n" +
            "  \"datetime\": \"1970-01-01\",\n" +
            "  \"unix_time\": 52200\n" +
            "}";

    private final String stocksSource = "{\n" +
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

    // start_date:  2020-05-06
    // end_date:    2020-05-08  the last date & value from API is always the day before (2020-05-07 instead of 2020-05-08)
    private final LocalDate startDate = LocalDate.of(2020, 5, 6);
    private final LocalDate endDate = LocalDate.of(2020, 5, 8);
    private final String timeSeriesSource = "{\n" +
            "  \"meta\": {\n" +
            "    \"symbol\": \"AAPL\",\n" +
            "    \"interval\": \"1day\",\n" +
            "    \"currency\": \"USD\",\n" +
            "    \"exchange_timezone\": \"America/New_York\",\n" +
            "    \"exchange\": \"NASDAQ\",\n" +
            "    \"mic_code\": \"XNGS\",\n" +
            "    \"type\": \"Common Stock\"\n" +
            "  },\n" +
            "  \"values\": [\n" +
            "    {\n" +
            "      \"datetime\": \"2020-05-07\",\n" +
            "      \"open\": \"75.81000\",\n" +
            "      \"high\": \"76.29000\",\n" +
            "      \"low\": \"75.49000\",\n" +
            "      \"close\": \"75.94000\",\n" +
            "      \"volume\": \"115215056\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"datetime\": \"2020-05-06\",\n" +
            "      \"open\": \"75.11000\",\n" +
            "      \"high\": \"75.81000\",\n" +
            "      \"low\": \"74.72000\",\n" +
            "      \"close\": \"75.16000\",\n" +
            "      \"volume\": \"142333760\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"ok\"\n" +
            "}";


    @Test
    void stocks() throws JsonProcessingException {
        JsonNode node = Json.parse(stocksSource);
        ApiStockDescriptionListPOJO pojo = Json.fromJson(node, ApiStockDescriptionListPOJO.class);
        String symbol = "";
        for (ApiStockDescriptionPOJO bP : pojo.getData()) {
            if(bP.getSymbol().equals("AACI")){
                symbol = bP.getSymbol();
            }
        }
        assertEquals("AACI",symbol);
    }

    @Test
    void earliestTimestamp() throws JsonProcessingException {
        JsonNode node = Json.parse(earliestTimeStampSource);
        ApiStockEarliestTimestampPOJO pojo = Json.fromJson(node, ApiStockEarliestTimestampPOJO.class);
        assertEquals("1970-01-01", pojo.getDatetime().toString());
    }

    @Test
    void timeSeries() throws JsonProcessingException {
        JsonNode node = Json.parse(timeSeriesSource);
        ApiStockValueListPOJO pojo = Json.fromJson(node, ApiStockValueListPOJO.class);
        for (ApiStockValuePOJO stockValue : pojo.getValues()) {
            if (stockValue.getDatetime().equals(startDate)) {
                assertEquals("2020-05-06",stockValue.getDatetime().toString());
                assertEquals(75.16000,stockValue.getClose());
            }
            if (stockValue.getDatetime().equals(endDate.minusDays(1))) {
                assertEquals("2020-05-07", stockValue.getDatetime().toString());
                assertEquals(75.94000, stockValue.getClose());
            }
        }
    }
}