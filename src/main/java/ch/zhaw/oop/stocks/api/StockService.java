package ch.zhaw.oop.stocks.api;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

/*  Die Klasse StockService bietet statische Methoden für HTTP GET Requests.

    Attribute:
    - HOSTNAME - wird in jedem Fall benötigt
    - APIKEY - wird teilweise benötigt
    Methoden:
    - stocks()                          -> StockDescriptionList -> StockDescription
    - earliest_timestamp(String symbol) -> StockEarliestTimestamp
    - time_series (div..)               -> StockValueList -> StockValue
 */


public class StockService {

    private static final String HOSTNAME =  "https://api.twelvedata.com/";
    private static final String APIKEY =    "1ee05fe17c2a42cfbbda5dd11e8fa496";

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
