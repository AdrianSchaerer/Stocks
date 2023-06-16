package ch.zhaw.oop.stocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
/**
 * FEM: A simple stock search engine that uses API calls to display time value of stocks.
 * Also provides the loss/gain for the defined period and exports the data to a CSV file.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@SpringBootApplication
public class StocksApplication {
	private String startDate = "0000-00-00";
	private String endDate = "0000-00-00";
	private String stockName = "PLACEHOLDER";
	private double startValue = 0.0;
	private double endValue = 0.0;
	private double gain = 0.0;
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(StocksApplication.class, args);
		/* 	FEM: Application context printout
		 *	Prints out a list of all initialized beans in spring container (Application Context)
		 * 	Usage: Debugging and development */
		for (String s : context.getBeanDefinitionNames()) {
			System.out.println(s);
		}
		StocksApplication app = context.getBean(StocksApplication.class);
		// Set Stock Data
		app.setStockData("2023-01-01", "2023-06-01", "ABC", 100.0, 120.0, 1234);
		// Create new Stock object
		ch.zhaw.oop.stocks.Stock stock = app.createStockObject();
		// Pass Stock object's data to CSV exporter
		app.exportStockDataToCsv(stock);

		context.close();
	}
	/* 	FEM: CSV Exporter creation method
	 *	Creates a new exporter object.
	 */
	@Bean
	public ch.zhaw.oop.stocks.CsvExporter csvExporter() {
		return new ch.zhaw.oop.stocks.CsvExporter();
	}
	/* 	FEM: Stock data setter
	 *	Sets the predefined (placeholder) stock data values
	 */
	private void setStockData(String startDate, String endDate, String stockName, double startValue, double endValue, double gain) throws Exception {
		// Set the stock data received from other methods
		try {
			this.startDate = startDate;
			this.endDate = endDate;
			this.stockName = stockName;
			this.startValue = startValue;
			this.endValue = endValue;
		}
		catch(Exception e) {
			// Handle exceptions
			e.printStackTrace();
			throw new Exception("Failed to set stock details: " + e.getMessage());
		}
		finally {
			// Close application context
			System.out.println("Stock details set");
		}
	}
	/* 	FEM: Stock creation method
	 *	Creates a new stock object and hands over the stock data params.
	 */
	private ch.zhaw.oop.stocks.Stock createStockObject() throws Exception {
		// Create a single stock object with the initialized data
		try {
			ch.zhaw.oop.stocks.Stock stock = new ch.zhaw.oop.stocks.Stock(startDate, endDate, stockName, startValue, endValue, gain);
			return stock;
		} catch(Exception e) {
			// Handle exceptions
			e.printStackTrace();
			throw new Exception("Failed to create new Stock: " + e.getMessage());
		}
		finally {
			// Close application context
			System.out.println("New stock object created");
		}
	}
	/* 	FEM: CSV export method
	 *	Exports the stock object's Data to a CSV file
	 */
	private void exportStockDataToCsv(ch.zhaw.oop.stocks.Stock stock) throws Exception{
		// Invoke methods from CsvExporter class
		try {
			ch.zhaw.oop.stocks.CsvExporter csvExporter = csvExporter();
			csvExporter.exportToCsv(stock);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to export data to CSV: " + e.getMessage());
		}
		finally {
			// Close application context
			System.out.println("CSV exported");
		}
	}
}

