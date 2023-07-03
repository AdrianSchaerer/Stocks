package ch.zhaw.oop.stocks.stocks;
import ch.zhaw.oop.stocks.api.ApiStockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StockControllerTest {
    private StockController stockController;
    private ApiStockService apiStockService;
    private Stock stock;
    // FEM: Create new stock object before each test.
    @BeforeEach
    public void setup() {
        StockService stockService = new StockService(apiStockService); // Create an instance of the StockService
        stockController = new StockController(new Stock(), stockService); // Create an instance of the StockController
        stock = new Stock(); // Create Stock instance
    }
        @Test
    public void testGetStock() {
        Stock stock = stockController.getStock();
        assertNull(stock.getStockName());
    }

    @Test
    public void testCreateStock() {
        StockRequest request = new StockRequest();
        request.setStartDate(LocalDate.of(2023, 1, 1));
        request.setEndDate(LocalDate.of(2023, 12, 31));
        request.setStockName("AAPL");
        request.setInvestValue(1000.0);

        Stock stock = stockController.createStock(request);

        assertEquals("AAPL", stock.getStockName());
    }
}
