package ch.zhaw.oop.stocks.api;

import ch.zhaw.oop.stocks.stocks.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;

/**
 *  <h1>StockDescription</h1>
 *  <p>FEM: Invokes an API call and returns its result.</p>
 *
 *  @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 *  @version 0.1
 */

// ADR: This class is not used at the moment
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ApiStockController {
    ApiStockService apiStockService;

    @Autowired
    public ApiStockController(ApiStockService apiStockService) {
        this.apiStockService = apiStockService;
    }

    // ADR: not implemented yet -> to get a list of available stocks for web
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/apiStocks")
    public String createStock(){
        ApiStockDescriptionList apiStockDescriptionList = apiStockService.stocks();
        HashMap<String,String> hashMap = new HashMap<>();
        for (ApiStockDescription apiStockDescription : apiStockDescriptionList.getData()) {
            hashMap.put(apiStockDescription.getSymbol(), apiStockDescription.getName());
        }
        return hashMap.toString();
    }

}