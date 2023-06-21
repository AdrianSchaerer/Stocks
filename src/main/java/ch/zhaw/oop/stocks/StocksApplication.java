package ch.zhaw.oop.stocks;
import ch.zhaw.oop.stocks.api.StockService;
import ch.zhaw.oop.stocks.api.StockValue;
import ch.zhaw.oop.stocks.api.StockValueList;
import ch.zhaw.oop.stocks.exporter.CsvExporter;
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

		// Retrieve the Stock, CsvImporter and CsvExporter beans from the application context
		ch.zhaw.oop.stocks.stocks.Stock stock = context.getBean(ch.zhaw.oop.stocks.stocks.Stock.class);
		ch.zhaw.oop.stocks.importer.CsvImporter csvImporter = context.getBean(ch.zhaw.oop.stocks.importer.CsvImporter.class);
		ch.zhaw.oop.stocks.exporter.CsvExporter csvExporter = context.getBean(ch.zhaw.oop.stocks.exporter.CsvExporter.class);

		// Create the stock object with placeholder data (could be received from Web API)
		createStockObject(stock, LocalDate.of(2022, 1, 12), LocalDate.of(2022, 12, 31), "AAPL", 2000.0);

		// Make the API call and update the stock object
		makeAPICall(stock.getStartDate(), stock.getEndDate(), stock.getStockName(), stock);
		System.out.println(stock);

		// Calculate gain/loss value and update the stock object
		calculateGainLoss(stock.getInvestValue(), stock.getFinalValue(), stock);

		// Export stock data to CSV and get the download URL
		String downloadUrl = exportStockDataToCSV(stock, context);
		System.out.println("Stock data exported to CSV file. Download URL: " + downloadUrl);
        /*
         *  TODO: FEM: The following block is used for a possible CsvImporter feature.
         *  This feature is currently not in use and therefore excluded from code.

         // Receive the CSV file from the web application and extract the filename and URL
         String filename = "example.csv"; // Replace with the actual filename received from the web application
         String fileUrl = "/csvimport/" + filename; // Replace with the actual URL received from the web application
         // Import the CSV file data and populate the Stock object
         csvImporter.importCSV(filename);
         System.out.println("CSV file imported: " + filename);
         */

		// Close the application context
		context.close();
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
	/** FEM: Invokes an API call and returns its result.
	 * @param startDate LocalDate start stock search period.
	 * @param endDate LocalDate end stock search period.
	 * @param stockName String unique stock name identifier.
	 * @param stock created stock object.
	 */
	private static void makeAPICall(LocalDate startDate, LocalDate endDate, String stockName, ch.zhaw.oop.stocks.stocks.Stock stock) throws Exception {
		try {
			// API Call to retrieve the value (close) between startDate and endDate
			StockValueList stockValueList = StockService.time_series(stockName, startDate, endDate);

			// Update the startValue and endValue in the Stock object
			for (StockValue stockValue : stockValueList.getValues()) {
				if (stockValue.getDatetime().equals(startDate)) {
					stock.setStartValue(stockValue.getClose());
					// to visualize the API call response
					System.out.println("Startwert: " + stockValue.getClose());
					System.out.println("Startdatum: " + startDate);
				}
				if (stockValue.getDatetime().equals(endDate.minusDays(1))) {
					stock.setEndValue(stockValue.getClose());
					// to visualize the API call response
					System.out.println("Endwert: " + stockValue.getClose());
					System.out.println("Enddatum: " + endDate.minusDays(1));
				}
			}
		} catch(Exception e) {
			// Handle exceptions
			e.printStackTrace();
			throw new Exception("Failed to make API call: " + e.getMessage());
		} finally {
			System.out.println("API call successfully made. Data written to stock object.");
		}
	}
	/** FEM: Invokes an API call and returns its result.
	 * @param investValue LocalDate start stock search period.
	 * @param finalValue LocalDate end stock search period.
	 * @param stock created stock object.
	 */
	private static void calculateGainLoss (double investValue, double finalValue, ch.zhaw.oop.stocks.stocks.Stock stock) throws Exception {
		try {
			double gainLossValue = finalValue - investValue;
			// Update the gainLossValue in the Stock object
			stock.setGainLossValue(gainLossValue);
		} catch(Exception e) {
			// Handle exceptions
			e.printStackTrace();
			throw new Exception("Failed to calculate gain or loss Value: " + e.getMessage());
		} finally {System.out.println("gain or loss Value calculated.");}
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

