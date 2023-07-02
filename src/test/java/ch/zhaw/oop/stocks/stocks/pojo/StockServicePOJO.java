package ch.zhaw.oop.stocks.stocks.pojo;

import ch.zhaw.oop.stocks.api.ApiStockService;
import ch.zhaw.oop.stocks.api.ApiStockValue;
import ch.zhaw.oop.stocks.api.ApiStockValueList;
import ch.zhaw.oop.stocks.api.pojo.ApiStockValueListPOJO;
import ch.zhaw.oop.stocks.api.pojo.ApiStockValuePOJO;
import ch.zhaw.oop.stocks.stocks.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <h1>StockService</h1>
 * The class StockService contains some static methods to fetch data from the Stock API
 * In this case the provider twelvedata is used.
 * @author      Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version     1.0
 * @since       2023-06-23
 */
@Service
public class StockServicePOJO {

    // ADR: Refactored from the ApiStockController
    private final ApiStockService apiStockService;

    @Autowired
    public StockServicePOJO(ApiStockService apiStockService) {
        this.apiStockService = apiStockService;
    }

    public Stock makeAPICall(Stock stock) throws Exception {
        double startValue = 0.0;
        double endValue = 0.0;

        try {
            // ADR: API Call to get a List with stock objects +10 Days after and -10 Days prior the user input asked
            ApiStockValueList apiStockValueList = apiStockService.timeSeries(stock.getStockName(), stock.getStartDate(), stock.getEndDate());

            // ADR: Start Date -> Test if there are Stocks available from the Web (User input), otherwise add 1 day until successful
//            stock = stockDateCheck(apiStockValueList, stock);

            // ADR: Search the values (close) for start and end Date and put it in startValue and endValue
            for (ApiStockValue stockValue : apiStockValueList.getValues()) {
                if (stockValue.getDatetime().equals(stock.getStartDate())) {
                    startValue = stockValue.getClose();
                }
                if (stockValue.getDatetime().equals(stock.getEndDate())) {
                    endValue = stockValue.getClose();
                }
            }
            // ADR: Set startValue and endValue into stock object:
            stock.setStockFromAPI(startValue, endValue);

        } catch(Exception e) {
            // FEM: Handle exceptions
            e.printStackTrace();
            throw new Exception("Failed to make API call: " + e.getMessage());
        }
        return stock;
    }

    // ADR: private method to check if there is a Stock available on this day otherwise look for the next one
    public static StockPOJO stockDateCheck(ApiStockValueListPOJO apiStockValueList, StockPOJO stock) {

        // ADR: Use a separate variable to check for a date which holds values
        LocalDate startDateNew = stock.getStartDate();
        LocalDate endDateNew = stock.getEndDate();

        // ADR: Check Start Date
        boolean dateAvailable = false;
        while (!dateAvailable) {
            for (ApiStockValuePOJO stockValue : apiStockValueList.getValues()) {
                if (stockValue.getDatetime().equals(startDateNew)) {
                    dateAvailable = true;
                    break;
                }
            }
            if ((startDateNew.isEqual(stock.getEndDate())) || (startDateNew.isAfter(stock.getEndDate()))){
                System.err.println("The Start Date is equal or after the End Date. Choose a wider Range for Start Date and End Date.");
                break;
            }
            if (!dateAvailable) {
                    startDateNew = startDateNew.plusDays(1);
            }
        }

        // ADR: Check End Date
        dateAvailable = false;
        while (!dateAvailable) {
            for (ApiStockValuePOJO stockValue : apiStockValueList.getValues()) {
                if (stockValue.getDatetime().equals(endDateNew)) {
                    dateAvailable = true;
                    break;
                }
            }
            if ((endDateNew.isEqual(stock.getStartDate())) || (endDateNew.isBefore(stock.getStartDate()))){
                System.err.println("The End Date is equal or after the Start Date. Choose a wider Range for Start Date and End Date.");
                break;
            }
            if (!dateAvailable) {
                endDateNew = endDateNew.minusDays(1);
            }
        }

        // ADR: Set the checked an adjusted new dates to the stock object and return it
        stock.setStartDate(startDateNew);
        stock.setEndDate(endDateNew);

        return stock;
    }
}
