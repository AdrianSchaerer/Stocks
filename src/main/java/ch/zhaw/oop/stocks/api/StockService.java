package ch.zhaw.oop.stocks.api;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

/**
 * <h1>StockService</h1>
 * The class StockService contains some static methods to fetch data from the Stock API
 * In this case the provider twelvedata is used.
 * @author      Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version     1.0
 * @since       2023-06-23
 */

public class StockService {

    private static final String HOSTNAME =  "https://api.twelvedata.com/";
    private static final String APIKEY =    "1ee05fe17c2a42cfbbda5dd11e8fa496";

    /**
     * <p>stocks()</p>
     * <p>This static method fetches a list of available stocks</p>
     * @return object StockDescriptionList with a list of objects StockDescription
     * @throws IOException
     * @throws InterruptedException
     */
    public static StockDescriptionList stocks() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HOSTNAME+
                        "stocks"+
                        "?exchange=NASDAQ"+
                        "&type=stock"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode node = Json.parse(response.body());
        return Json.fromJson(node, StockDescriptionList.class);
    }

    /**
     * <p>earliest_timestamp()</p>
     * <p>This static method fetches the date since when the specific stock is traded on the market.</p>
     * @param symbol of the stock
     * @return object StockEarliestTimestamp
     * @throws IOException
     * @throws InterruptedException
     */
    public static StockEarliestTimestamp earliest_timestamp(String symbol) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HOSTNAME +
                        "earliest_timestamp" +
                        "?apikey=" + APIKEY +
                        "&symbol=" + symbol +
                        "&type=stock" +
                        "&interval=1day"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode node = Json.parse(response.body());
        return Json.fromJson(node, StockEarliestTimestamp.class);
    }

    /**
     * <p>time_series()</p>
     * <p>This static method fetches data in between a start and an end date of a specific stock.</p>
     * @param symbol of the stock
     * @param startDate start date
     * @param endDate end date
     * @return object StockValueList which contains a list of StockValues
     * @throws IOException
     * @throws InterruptedException
     */
    public static StockValueList time_series(String symbol, LocalDate startDate, LocalDate endDate) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HOSTNAME +
                        "time_series" +
                        "?start_date=" + startDate +
                        "&symbol=" + symbol +
                        "&interval=1day" +
                        "&type=stock" +
                        "&apikey=" + APIKEY +
                        "&end_date=" + endDate))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        if(response.statusCode() != 200){
//            System.out.println("Es konnte kein API call abgesetzt werden.");
//        };
        JsonNode node = Json.parse(response.body());
        return Json.fromJson(node, StockValueList.class);
    }

}
