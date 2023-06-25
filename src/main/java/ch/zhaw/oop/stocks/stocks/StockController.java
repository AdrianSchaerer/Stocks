package ch.zhaw.oop.stocks.stocks;

import ch.zhaw.oop.stocks.api.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * FEM: Stock object controller class which is used to create and get stock data.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class StockController {

    private final ch.zhaw.oop.stocks.stocks.Stock stock;
    /**
     * FEM: Constructor for the stock controller
     * Autoinjects stock when controller instance is created.
     * Provides proper initialization with required stock dependency.
     */
    @Autowired
    public StockController(ch.zhaw.oop.stocks.stocks.Stock stock) {this.stock = stock;}
    /**
     * FEM: Placeholder HTTP GET Mapping
     * Maps the GET Request to /stock to the stock object (@GetMapping annotation).
     * Also converts it to appropriate form (@ResponseBody annotation).
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/stocks")
    @ResponseBody
    public Stock getStock() {
        return stock;
    }

    /**
     * FEM: Creates a new stock object with JSON data from frontend
     * @param request Stock Data as JSON from frontend, using the StockRequest class
     * @return Stock data
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/stocks")
    public Stock createStock(@RequestBody StockRequest request) throws Exception {
        // FEM: Create the stock object using the data from the request
        Stock stock = new Stock();
        stock.setStockFromWeb(request.getStartDate(),request.getEndDate(),request.getStockName(),request.getInvestValue());

        // FEM: Make the API call to retrieve startValue and endValue. Return api results to stock object.
        ApiController apiController = new ApiController();
        apiController.makeAPICall(stock.getStartDate(), stock.getEndDate(), stock.getStockName(), stock);
//        stock.setStockFromAPI(apiResult.getStartValue(),apiResult.getEndValue()); // Already done in APICall method

        // FEM: Perform additional processing or business logic

        return stock;
    }

}