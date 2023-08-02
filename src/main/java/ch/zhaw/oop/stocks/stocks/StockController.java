package ch.zhaw.oop.stocks.stocks;

import ch.zhaw.oop.stocks.model.Stocks;
import ch.zhaw.oop.stocks.service.implementation.StocksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>FEM: Stock object controller class which is used to create and get stock data.</p>
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@RestController
public class StockController {

    /**
     * <p>FEM: Declares private Stock Variable. Could be autowired.
     */
    @Autowired
    private Stock stock;
    private final StockService stockService;
    private final StocksServiceImpl stocksService;

    /**
     * FEM: Constructor for the stock controller
     * Autoinjects stock when controller instance is created.
     * Provides proper initialization with required stock dependency.
     *
     * @param stock        The Stock object to be injected.
     * @param stockService The StockService object to be injected.
     */
    @Autowired
    public StockController(Stock stock, StockService stockService, StocksServiceImpl stocksService) {
        this.stock = stock;
        this.stockService = stockService;
        this.stocksService = stocksService;
    }

    /**
     * FEM: Returns stock.
     *
     * @return the stock
     */
    @GetMapping("/stocks") // FEM: Maps the GET Request to /stocks to the stock object.
    @ResponseBody
    public Stock getStock() {
        return stock;
    }

    /**
     * FEM: Creates a new Stock object using the data from the request, makes an API call to retrieve additional data,
     * Stock performs additional processing and returns the complete Stock object.
     *
     * @param request The StockRequest object containing the data needed to create the Stock.
     * @return The Stock object that has been created, updated with API data, and processed.
     * @throws RuntimeException If an exception occurs during the API call or processing.
     */
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
        
        // ADR: To add the information to the database
        Stocks stocksdb = new Stocks();

        stocksdb.setStartDate(stock.getStartDate());
        stocksdb.setEndDate(stock.getEndDate());
        stocksdb.setStockName(stock.getStockName());
        stocksdb.setStartValue(stock.getStartValue());
        stocksdb.setEndValue(stock.getEndValue());
        stocksdb.setInvestValue(stock.getInvestValue());
        stocksdb.setFinalValue(stock.getFinalValue());
        stocksdb.setGainLossValue(stock.getGainLossValue());

        stocksService.create(stocksdb);
        
        // ADR: Return stock with all completed Data.
        return stock;
    }

}