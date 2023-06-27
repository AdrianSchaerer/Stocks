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
            // FEM: API Call to retrieve the value (close) between startDate and endDate
            StockValueList stockValueList = StockService.time_series(stockName, startDate, endDate);

            // FEM: Update the startValue and endValue in the Stock object
            for (StockValue stockValue : stockValueList.getValues()) {
                if (stockValue.getDatetime().equals(startDate)) {
                    stock.setStartValue(stockValue.getClose());
                    // FEM: to visualize the API call response
                    System.out.println("Startwert: " + stockValue.getClose());
                    System.out.println("Startdatum: " + startDate);
                }
                if (stockValue.getDatetime().equals(endDate.minusDays(1))) {
                    stock.setEndValue(stockValue.getClose());
                    // FEM: to visualize the API call response
                    System.out.println("Endwert: " + stockValue.getClose());
                    System.out.println("Enddatum: " + endDate.minusDays(1));
                }
            }
            // SCA: Adding the finalValue up to two digits after comma and gainLossValue
            if (stock.getStartValue() == 0) {
                System.err.println("Division by zero. StartValue: " + stock.getStartValue());
            } else {
                stock.setGainLossValue(stock.getEndValue()/ stock.getStartValue());
                System.out.println("gainLossValue: "+stock.getGainLossValue());
                stock.setEndValue(stock.getInvestValue()*stock.getGainLossValue());
                stock.setEndValue(Math.round(stock.getEndValue()*100.0)/100.0);
                System.out.println("finalValue: "+stock.getEndValue());
            }
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
