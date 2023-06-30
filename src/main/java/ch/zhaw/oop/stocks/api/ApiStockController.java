package ch.zhaw.oop.stocks.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
/** FEM: Invokes an API call and returns its result.
 */

// ADR: This class is not used at the moment
@Controller
public class ApiStockController {

    @Autowired
    public ApiStockController() { }
    /* FEM: API Call method.
     * @param startDate LocalDate start stock search period.
     * @param endDate LocalDate end stock search period.
     * @param stockName String unique stock name identifier.
     * @param stock created stock object.
     */

    // ADR: Refactored the Method to the StockService class

}