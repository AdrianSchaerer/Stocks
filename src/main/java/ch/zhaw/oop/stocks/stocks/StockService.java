package ch.zhaw.oop.stocks.stocks;

import ch.zhaw.oop.stocks.api.ApiStockService;
import ch.zhaw.oop.stocks.api.ApiStockValue;
import ch.zhaw.oop.stocks.api.ApiStockValueList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <h1>StockService</h1>
 * <p>FEM: The class StockService contains some static methods to fetch data from the Stock API</p>
 * <p>In this case the provider twelvedata is used.</p>
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@Service
public class StockService {

    // ADR: Refactored from the ApiStockController
    private final ApiStockService apiStockService;

    /**
     * FEM: Constructs a StockService instance.
     *
     * @param apiStockService the API stock service dependency
     */
    @Autowired
    public StockService(ApiStockService apiStockService) {
        this.apiStockService = apiStockService;
    }
    /**
     * FEM: Makes an API call to fetch stock data and updates the provided Stock object with start and end values.
     *
     * @param stock the Stock object to update
     * @return the updated Stock object
     * @throws Exception if there is an error making the API call
     */
    public Stock makeAPICall(Stock stock) throws Exception {
        double startValue = 0.0;
        double endValue = 0.0;

        try {
            // ADR: API Call to get a List with stock objects +10 Days after and -10 Days prior the user input asked
            ApiStockValueList apiStockValueList = apiStockService.timeSeries(stock.getStockName(), stock.getStartDate(), stock.getEndDate());

            // ADR: Start Date -> Test if there are Stocks available from the Web (User input), otherwise add 1 day until successful
            stock = stockDateCheck(apiStockValueList, stock);

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

    /**
     * ADR/FEM: Checks if there is a stock available on a given date and adjusts the start and end dates accordingly.
     *
     * @param apiStockValueList the list of stock values from the API
     * @param stock the Stock object to adjust the dates for
     * @return the adjusted Stock object
     */
    private static Stock stockDateCheck(ApiStockValueList apiStockValueList, Stock stock) {

        // ADR: Use a separate variable to check for a date which holds values
        LocalDate startDateNew = stock.getStartDate();
        LocalDate endDateNew = stock.getEndDate();

        // ADR: Check Start Date
        boolean dateAvailable = false;
        while (!dateAvailable) {
            for (ApiStockValue stockValue : apiStockValueList.getValues()) {
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
            for (ApiStockValue stockValue : apiStockValueList.getValues()) {
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
