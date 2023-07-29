package ch.zhaw.oop.stocks.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

/**
 * <p>The class ApiStockService contains some methods to fetch data from the web API</p>
 * <p>In this case the provider twelvedata is used.</p>
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@Service
@NoArgsConstructor
public class ApiStockService {
    private static final String HOSTNAME =  "https://api.twelvedata.com/";
    private static final String APIKEY =    "1ee05fe17c2a42cfbbda5dd11e8fa496";

    /**
     * <p>stocks()</p>
     * <p>This method fetches a list of available stocks</p>
     *
     * @return object ApiDescriptionList with a list of objects StockDescription
     */
    public ApiStockDescriptionList stocks(){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HOSTNAME+
                        "stocks"+
                        "?exchange=NASDAQ"+
                        "&type=stock"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        JsonNode node = makeHttpRequest(request);
        try {
            return Json.fromJson(node, ApiStockDescriptionList.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to map the JsonNode to the class ApiDescriptionList: "+e.getMessage());
        }
    }

    /**
     * <p>earliestTimestamp()</p>
     * <p>This method fetches the date since when the specific stock is traded on the market.</p>
     *
     * @param symbol of the stock
     * @return object StockEarliestTimestamp
     */
    public ApiStockEarliestTimestamp earliestTimestamp(String symbol){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HOSTNAME +
                        "earliest_timestamp" +
                        "?apikey=" + APIKEY +
                        "&symbol=" + symbol +
                        "&type=stock" +
                        "&interval=1day"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        JsonNode node = makeHttpRequest(request);
        try {
            return Json.fromJson(node, ApiStockEarliestTimestamp.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to map the JsonNode to the class StockEarliestTimestamp: "+e.getMessage());
        }
    }

    /**
     * <p>timeSeries(...)</p>
     * <p>This method fetches data in between a start and an end date of a specific stock.</p>
     *
     * @param symbol    of the stock
     * @param startDate start date
     * @param endDate   end date
     * @return object StockValueList which contains a list of StockValues
     */
    public ApiStockValueList timeSeries(String symbol, LocalDate startDate, LocalDate endDate) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HOSTNAME +
                        "time_series" +
                        "?start_date=" + startDate.minusDays(10) +
                        "&symbol=" + symbol +
                        "&interval=1day" +
                        "&type=stock" +
                        "&apikey=" + APIKEY +
                        "&end_date=" + endDate.plusDays(10)))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        JsonNode node = makeHttpRequest(request);
        try {
            return Json.fromJson(node, ApiStockValueList.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to map the JsonNode to the class StockValueList: "+e.getMessage());
        }
    }

    /**
     * <p>makeHttpRequest()</p>
     * <p>To reduce the amount of code if enclosed with try-catch. </p>
     * @param request the object from class HttpRequest
     * @return JsonNode
     */
    private JsonNode makeHttpRequest(HttpRequest request) {
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to make Http request: " + e.getMessage());
        }
        JsonNode node = null;
        try {
            node = Json.parse(response.body());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse the Http request to JsonNode: "+e.getMessage());
        }
        return node;
    }

}
