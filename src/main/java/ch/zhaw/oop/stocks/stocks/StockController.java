package ch.zhaw.oop.stocks.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * FEM: Stock object controller class which is used to create and get stock data.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
// TRD: Added Cors Handling
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StockController {

    private Stock stock;
    private final StockService stockService;
    /**
     * FEM: Constructor for the stock controller
     * Autoinjects stock when controller instance is created.
     * Provides proper initialization with required stock dependency.
     */
    // ADR: Added DI for StockService
    @Autowired
    public StockController(Stock stock, StockService stockService) {
        this.stock = stock;
        this.stockService = stockService;
    }
    /**
     * FEM: Placeholder HTTP GET Mapping
     * Maps the GET Request to /stock to the stock object (@GetMapping annotation).
     * Also converts it to appropriate form (@ResponseBody annotation).
     */
    // TRD: Added Cors Handling
    @CrossOrigin(origins = "http://localhost:4200")
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
    // TRD: Added Cors Handling
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/stocks")
    public Stock createStock(@RequestBody StockRequest request){
        // FEM: Create the stock object using the data from the request
        // ADR: used the injected stock object and returned
        stock.setStockFromWeb(request.getStartDate(),request.getEndDate(),request.getStockName(),request.getInvestValue());

        // ADR: To Test -> Info from User
        System.out.println("Web: Startdatum:    "+stock.getStartDate());
        System.out.println("Web: Enddatum:      "+stock.getEndDate());
        // FEM: Make the API call to retrieve startValue and endValue. Return api results to stock object.
        // ADR: DI the apiController in the constructor
        // ADR: We send the stock object to be completed by stockService and sent back here to be returned afterward
        try {
            stock = stockService.makeAPICall(stock);
            // ADR: To Test -> the dates are checked (no weekends or holidays are possible)
            System.out.println("API: Startdatum:    "+stock.getStartDate());
            System.out.println("API: StartValue:    "+stock.getStartValue());
            System.out.println("API: Enddatum:      "+stock.getEndDate());
            System.out.println("API: EndValue:      "+stock.getEndValue());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // FEM: Perform additional processing or business logic

        // ADR: Added return stock which is now complete with all data
        return stock;
    }

}