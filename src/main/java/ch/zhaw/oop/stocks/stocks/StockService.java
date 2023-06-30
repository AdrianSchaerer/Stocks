package ch.zhaw.oop.stocks.stocks;

import ch.zhaw.oop.stocks.api.ApiStockService;
import ch.zhaw.oop.stocks.api.ApiStockValue;
import ch.zhaw.oop.stocks.api.ApiStockValueList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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
            // FEM: API Call to retrieve the value (close) between startDate and endDate
            ApiStockValueList stockValueList = apiStockService.timeSeries(stock.getStockName(), stock.getStartDate(), stock.getEndDate());
            // FEM: Update the startValue and endValue in the Stock object
            for (ApiStockValue stockValue : stockValueList.getValues()) {
                if (stockValue.getDatetime().equals(stock.getStartDate())) {
                    startValue = stockValue.getClose();
                    // FEM: to visualize the API call response
                    System.out.println("API Startdatum: " + stock.getStartDate());
                    System.out.println("API Startwert: " + stockValue.getClose());
                }
                if (stockValue.getDatetime().equals(stock.getEndDate())) {
                    endValue = stockValue.getClose();
                    // FEM: to visualize the API call response
                    System.out.println("API Enddatum: " + stock.getEndDate());
                    System.out.println("API Endwert: " + stockValue.getClose());
                }
            }
            stock.setStockFromAPI(startValue, endValue);

            System.out.println("API call successfully made. Data written to stock object.");
        } catch(Exception e) {
            // FEM: Handle exceptions
            e.printStackTrace();
            throw new Exception("Failed to make API call: " + e.getMessage());
        }
        return stock;
    }

}
