package ch.zhaw.oop.stocks;

import ch.zhaw.oop.stocks.model.Stocks;
import ch.zhaw.oop.stocks.repo.StocksRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Stock Application is initialized...");
		// FEM: Start Spring Boot Application, load Beans and start Server
		SpringApplication.run(StocksApplication.class, args);
	}

	@Bean
	CommandLineRunner run(StocksRepo stocksRepo){
        return args -> {
			stocksRepo.save(new Stocks(null, LocalDate.of(2021,10,1),LocalDate.of(2022,10,1),"AAPL",100,110,1000,1100,100));
			stocksRepo.save(new Stocks(null, LocalDate.of(2020,10,1),LocalDate.of(2022,10,1),"AAPL",90,110,1000,1200,200));
			stocksRepo.save(new Stocks(null, LocalDate.of(2019,10,1),LocalDate.of(2022,10,1),"AAPL",80,110,1000,1300,300));
		};
    }
}

