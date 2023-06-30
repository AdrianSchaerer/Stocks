package ch.zhaw.oop.stocks.stocks;

import ch.zhaw.oop.stocks.api.ApiStockService;
import ch.zhaw.oop.stocks.api.ApiStockValue;
import ch.zhaw.oop.stocks.api.ApiStockValueList;
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
public class StockService {

    // ADR: Refactored from the ApiStockController
    private final ApiStockService apiStockService;

    @Autowired
    public StockService(ApiStockService apiStockService) {
        this.apiStockService = apiStockService;
    }

    public Stock makeAPICall(Stock stock) throws Exception {
        double startValue = 0.0;
        double endValue = 0.0;

        try {
            // ADR: API Call to get a List with stock objects +10 Days after and -10 Days prior the user input asked
            ApiStockValueList apiStockValueList = apiStockService.timeSeries(stock.getStockName(), stock.getStartDate(), stock.getEndDate());

            // ADR: Start Date -> Test if there are Stocks available from the Web (User input), otherwise add 1 day until successful
            boolean startDateBoolean = true;
            LocalDate startDateNew = stockAvailable(apiStockValueList, stock.getStartDate(), startDateBoolean);
            startDateBoolean = false;
            LocalDate endDateNew = stockAvailable(apiStockValueList, stock.getEndDate(),startDateBoolean);

            // ADR: Set the corrected values for retrieving data
            stock.setStartDate(startDateNew);
            stock.setEndDate(endDateNew);

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

            // ADR: This part of the code should now be obsolete
            if (stock.getStartValue()==0) {
                System.err.println("Start Date: "+stock.getStartDate()+" There were no stocks traded on this date: ");
            }
            if (stock.getEndValue()==0){
                System.err.println("End Date: "+stock.getEndDate()+" There were no stocks traded on this date: ");
            }

            if ((stock.getStartValue() != 0) && (stock.getEndValue() != 0)) {
                System.out.println("API call successfully made. Data written to stock object.");
            } else {
                System.out.println("There seems to be a problem with either start or enddate.");
            }


        } catch(Exception e) {
            // FEM: Handle exceptions
            e.printStackTrace();
            throw new Exception("Failed to make API call: " + e.getMessage());
        }
        return stock;
    }

    // ADR: private method to check if there is a Stock available on this day otherwise look for the next one
    private LocalDate stockAvailable(ApiStockValueList apiStockValueList, LocalDate localDate, boolean startDateBoolean) {
        boolean dateAvailable = false;
        while (!dateAvailable) {
            for (ApiStockValue stockValue : apiStockValueList.getValues()) {
                if (stockValue.getDatetime().equals(localDate)) {
                    dateAvailable = true;
                    break;
                }
            }
            if (!dateAvailable) {
                if (startDateBoolean) {
                    localDate = localDate.plusDays(1);
                } else {
                    localDate = localDate.minusDays(1);
                }
            }
        }
        return localDate;
    }
}
