package ch.zhaw.oop.stocks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
/**
 * FEM: A simple stock search engine that uses API calls to display time value of stocks.
 * Also provides the loss/gain for the defined period and exports the data to a CSV file.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@SpringBootApplication
public class StocksApplication {
	public static void main(String[] args) throws Exception {
		ApplicationContext apc = SpringApplication.run(StocksApplication.class, args);
		/* 	FEM: Application context printout
		*	Prints out a list of all initialized beans in spring container (Application Context)
		* 	Usage: Debugging and development */
		for (String s : apc.getBeanDefinitionNames()) {
			System.out.println(s);
		}
		/* FEM: Methods to run or to be tested und start: */
		createStock();
	}
	/* 	FEM: Stock creation method
	 *	Creates a new stock object and hands over the stock data params.
	 */
	static void createStock() throws Exception {
		try {
		Stock stock = new Stock("2021-03-23","2021-06-06","AAPO", 254,574,41);
		System.out.println("I just created a stock");
		}
		catch(Exception e) {
			// Handle exceptions
			e.printStackTrace();
			throw new Exception("Failed to create new Stock: " + e.getMessage());
		}
		finally {
			// Close application context
			System.out.println("Create Stock exit");
		}
	}
	/* 	FEM: CSV export method
	 *	Exports the stock object's Data to a CSV file
	 */
	public void csvExport() throws Exception {
		try {
			// TBD: Create new Exporter and handover Data from Object
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
