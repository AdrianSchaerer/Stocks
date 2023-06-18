package ch.zhaw.oop.stocks.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * FEM: Stock object controller class which is used to create and get stock data.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@Controller
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
     * Also converts it to apropriate form (@ResponseBody annotation).
     */
    @GetMapping("/stock")
    @ResponseBody
    public Stock getStock() {
        return stock;
    }
}