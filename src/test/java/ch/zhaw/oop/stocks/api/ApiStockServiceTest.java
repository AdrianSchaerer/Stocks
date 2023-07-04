package ch.zhaw.oop.stocks.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Api stock service test.
 */
class ApiStockServiceTest {

    private final String earliestTimeStampSource = "{\n" +
            "  \"datetime\": \"1970-01-01\",\n" +
            "  \"unix_time\": 52200\n" +
            "}";

    /**
     *
     */
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

    /**
     * Stocks test.
     *
     * @throws JsonProcessingException the json processing exception
     */
    @Test
    void stocksTest() throws JsonProcessingException {
        JsonNode node = Json.parse(stocksSource);
        ApiStockDescriptionList apiStockDescriptionList = Json.fromJson(node, ApiStockDescriptionList.class);
        String symbol = "";
        for (ApiStockDescription apiStockDescription : apiStockDescriptionList.getData()) {
            if(apiStockDescription.getSymbol().equals("AACI")){
                symbol = apiStockDescription.getSymbol();
            }
        }
        assertEquals("AACI",symbol);
    }

    /**
     * Earliest timestamp test.
     *
     * @throws JsonProcessingException the json processing exception
     */
    @Test
    void earliestTimestampTest() throws JsonProcessingException {
        JsonNode node = Json.parse(earliestTimeStampSource);
        ApiStockEarliestTimestamp apiStockEarliestTimestamp = Json.fromJson(node, ApiStockEarliestTimestamp.class);
        assertEquals("1970-01-01", apiStockEarliestTimestamp.getDatetime().toString());
    }

    /**
     * Time series test.
     *
     * @throws JsonProcessingException the json processing exception
     */
    @Test
    void timeSeriesTest() throws JsonProcessingException {
        JsonNode node = Json.parse(timeSeriesSource);
        ApiStockValueList apiStockValueList = Json.fromJson(node, ApiStockValueList.class);
        for (ApiStockValue stockValue : apiStockValueList.getValues()) {
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