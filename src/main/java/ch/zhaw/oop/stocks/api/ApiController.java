package ch.zhaw.oop.stocks.api;

import ch.zhaw.oop.stocks.importer.CsvImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
/** FEM: Invokes an API call and returns its result.
 */
@Controller
public class ApiController {

    @Autowired
    public ApiController() { }
    /* FEM: API Call method.
     * @param startDate LocalDate start stock search period.
     * @param endDate LocalDate end stock search period.
     * @param stockName String unique stock name identifier.
     * @param stock created stock object.
     */
    public void makeAPICall(LocalDate startDate, LocalDate endDate, String stockName, ch.zhaw.oop.stocks.stocks.Stock stock) throws Exception {
        try {
            double startValue = 0;
            double endValue = 0;
            // FEM: API Call to retrieve the value (close) between startDate and endDate
            StockValueList stockValueList = StockService.time_series(stockName, startDate, endDate);
            // FEM: Update the startValue and endValue in the Stock object
            for (StockValue stockValue : stockValueList.getValues()) {
                if (stockValue.getDatetime().equals(startDate)) {
                    startValue = stockValue.getClose();
                    // FEM: to visualize the API call response
                    System.out.println("API Startwert: " + stockValue.getClose());
                    System.out.println("API Startdatum: " + startDate);
                }
                if (stockValue.getDatetime().equals(endDate.minusDays(1))) {
                    endValue = stockValue.getClose();
                    // FEM: to visualize the API call response
                    System.out.println("API Endwert: " + stockValue.getClose());
                    System.out.println("API Enddatum: " + endDate.minusDays(1));
                }
            }
            stock.setStockFromAPI(startValue, endValue);
        } catch(Exception e) {
            // FEM: Handle exceptions
            e.printStackTrace();
            throw new Exception("Failed to make API call: " + e.getMessage());
        } finally {
            //ToDo SCA: Attention: finally will be executed even if an exception is thrown!
            System.out.println("API call successfully made. Data written to stock object.");
        }
    }
}