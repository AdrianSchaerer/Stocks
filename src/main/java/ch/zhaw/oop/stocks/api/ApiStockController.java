package ch.zhaw.oop.stocks.api;

import ch.zhaw.oop.stocks.stocks.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>FEM: Invokes an API call and returns its result.</p>
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
// ADR: This class is not used at the moment
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ApiStockController {
    /**
     * The Api stock service.
     */
    ApiStockService apiStockService;

    /**
     * Instantiates a new Api stock controller.
     *
     * @param apiStockService the api stock service
     */
    @Autowired
    public ApiStockController(ApiStockService apiStockService) {
        this.apiStockService = apiStockService;
    }

    /**
     * Create stock string.
     *
     * @return string
     */
// ADR: not implemented yet -> to get a list of available stocks for web
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/apiStocks")
    public Map<String,String> availableStocks(){
        ApiStockDescriptionList apiStockDescriptionList = apiStockService.stocks();

        // Variante 1 - HashMap
        HashMap<String,String> hashMap = new HashMap<>();
        for (ApiStockDescription apiStockDescription : apiStockDescriptionList.getData()) {
            hashMap.put(apiStockDescription.getSymbol(), apiStockDescription.getName());
        }
        return hashMap;

        // Variante 2 - List
//        List<String> list = new ArrayList<>();
//        for (ApiStockDescription apiStockDescription : apiStockDescriptionList.getData()) {
////            list.add(apiStockDescription.getName());
//            list.add(apiStockDescription.getSymbol());
//        }
        // return list

    }
}