package ch.zhaw.oop.stocks.stocks;
import ch.zhaw.oop.stocks.api.ApiStockService;
import ch.zhaw.oop.stocks.api.ApiStockValue;
import ch.zhaw.oop.stocks.api.ApiStockValueList;
import ch.zhaw.oop.stocks.stocks.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StockServiceTest {

    private StockService stockService;
    private ApiStockService apiStockService;

    @BeforeEach
    public void setup() {
        apiStockService = new ApiStockService(); // Create an instance of the actual ApiStockService
        stockService = new StockService(apiStockService);
    }

    @Test
    public void testMakeAPICall_Successful() throws Exception {
        // Create a sample Stock object
        Stock stock = new Stock();
        stock.setStockName("AAPL");
        stock.setStartDate(LocalDate.parse("2023-01-01"));
        stock.setEndDate(LocalDate.parse("2023-12-31"));

        // Call the method under test
        Stock updatedStock = stockService.makeAPICall(stock);

        // Verify the expected behavior
        assertEquals(LocalDate.parse("2023-01-01"), updatedStock.getStartDate());
        assertEquals(LocalDate.parse("2023-12-31"), updatedStock.getEndDate());
        // Additional assertions based on the actual API response

        // Note: Since the test uses the actual API service, the behavior and results may vary based on real data and API availability.
        // Make sure to handle any exceptions that might occur during the API call.
    }

    @Test
    public void testMakeAPICall_Exception() {
        // Create a sample Stock object
        Stock stock = new Stock();
        stock.setStockName("INVALID_STOCK");
        stock.setStartDate(LocalDate.parse("2023-01-01"));
        stock.setEndDate(LocalDate.parse("2023-12-31"));

        // Call the method under test and expect an exception
        assertThrows(Exception.class, () -> stockService.makeAPICall(stock));

        // Note: This test case assumes that an exception will be thrown for an invalid stock name.
        // Adjust the expectations based on the actual behavior of the API service.
    }
}
