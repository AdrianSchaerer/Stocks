package ch.zhaw.oop.stocks;
import ch.zhaw.oop.stocks.api.ApiController;
import ch.zhaw.oop.stocks.api.StockService;
import ch.zhaw.oop.stocks.api.StockValue;
import ch.zhaw.oop.stocks.api.StockValueList;
import ch.zhaw.oop.stocks.exporter.CsvExporter;
import ch.zhaw.oop.stocks.importer.CsvImporter;
import ch.zhaw.oop.stocks.stocks.Stock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.ConfigurableApplicationContext;
import java.io.IOException;
import java.time.LocalDate;

/**
 * FEM: A simple stock search engine that uses API calls to display time value of stocks.
 * Also provides the loss/gain for the defined period and exports the data to a CSV file.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@SpringBootApplication(scanBasePackages={"ch.zhaw.oop.stocks"})
public class StocksApplication {
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(StocksApplication.class, args);
		StocksApplication app = context.getBean(StocksApplication.class);

		// FEM: Retrieve the Stock, CsvImporter and CsvExporter beans from the application context
		ch.zhaw.oop.stocks.stocks.Stock stock = context.getBean(ch.zhaw.oop.stocks.stocks.Stock.class);

		/* FEM: Create the stock object with placeholder data.
		*  Needs to be im place or nullpointer-exception occurs tue to missing data.
		*/
		createStockObject(stock, LocalDate.of(2022, 1, 2), LocalDate.of(2023, 1, 1), "AAPL", 2000.0);
		// SCA: To test the calculation of finalValue, gainLossValue
		makeAPICall(stock.getStartDate(), stock.getEndDate(), stock.getStockName(), stock);

		// FEM: Export stock data to CSV and get the download URL
		String downloadUrl = exportStockDataToCSV(stock, context);
		System.out.println("Stock data exported to CSV file. Download URL: " + downloadUrl);

	//	context.close();
	}
	/** FEM: Creates a new Stock object. The parameters may be passed over from other methods.
	 * @param stock new stock object to be created.
	 * @param startDate LocalDate start stock search period.
	 * @param endDate LocalDate end stock search period.
	 * @param stockName String unique stock name identifier.
	 * @param investValue double user defined investment value (usually USD for US stock exchange).
	 */
	private static void createStockObject(ch.zhaw.oop.stocks.stocks.Stock stock, LocalDate startDate, LocalDate endDate, String stockName, double investValue) {
		stock.setStartDate(startDate);
		stock.setEndDate(endDate);
		stock.setStockName(stockName);
		stock.setInvestValue(investValue);
	}

	private static void makeAPICall(LocalDate startDate, LocalDate endDate, String stockName, ch.zhaw.oop.stocks.stocks.Stock stock) throws Exception {
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
			System.out.println("API call successfully made. Data written to stock object.");
		}
	}

	/** FEM: Exports the stock object's data to a CSV file and returns a download url.
	 * @param context Ensures that the application context from main method is used.
	 * @param stock created stock object.
	 * @return downloadURL as a String
	 */
	private static String exportStockDataToCSV(Stock stock, ConfigurableApplicationContext context) throws Exception {
		try {
			CsvExporter csvExporter = context.getBean(CsvExporter.class);
			String downloadUrl = csvExporter.exportToCSV();
			return downloadUrl;
		} catch (IOException e) {
			System.out.println("Failed to export stock data to CSV file: " + e.getMessage());
			return null;
		} finally {System.out.println("CSV Data exported.");}
	}
}

