package ch.zhaw.oop.stocks.stocks;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class StockControllerTest {
    private Stock stock;
    private StockService stockService;
    private StockController stockController;

    @BeforeEach
    public void setup() {
        stockService = mock(StockService.class);
        stockController = new StockController(new Stock(), stockService);
    }
    @Test
    public void testCreateStock_Successful() {
        // Arrange
        StockRequest request = new StockRequest();
        request.setStartDate(LocalDate.of(2016, 4, 4));
        request.setEndDate(LocalDate.of(2018, 11, 12));
        request.setStockName("AAPL");
        request.setInvestValue(1000.0);

        double startValue = 100.0;
        double endValue = 110.0;

        Stock stock = new Stock();
        stock.setStartValue(startValue);
        stock.setEndValue(endValue);

        try {
            when(stockService.makeAPICall(any(Stock.class))).thenReturn(stock);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Act
        Stock result = new StockController(stock, stockService).createStock(request);

        // Assert
        assertEquals(request.getStartDate(), result.getStartDate());
        assertEquals(request.getEndDate(), result.getEndDate());
        assertEquals(request.getStockName(), result.getStockName());
        assertEquals(request.getInvestValue(), result.getInvestValue());
        assertEquals(startValue, result.getStartValue());
        assertEquals(endValue, result.getEndValue());

        // Verify that the makeAPICall method was called once
        try {
            verify(stockService, times(1)).makeAPICall(any(Stock.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCreateStock_Exception() throws Exception {
        // Arrange
        StockRequest request = new StockRequest();

        // Mockito Argument Captor: Captures the Stock argument data.
        ArgumentCaptor<Stock> stockCaptor = ArgumentCaptor.forClass(Stock.class);

        // Mock the makeAPICall method to throw an exception
        when(stockService.makeAPICall(stockCaptor.capture())).thenThrow(new Exception("API error"));

        // Act & Assert
        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            stockController.createStock(request);
        });

        // Check if the actual error message contains the expected error message
        assertTrue(exception.getMessage().contains("API error"));

        // Get the captured Stock object
        Stock capturedStock = stockCaptor.getValue();

        // Manually compare the properties of the captured Stock object
        assertEquals(request.getStartDate(), capturedStock.getStartDate());
        assertEquals(request.getEndDate(), capturedStock.getEndDate());
        assertEquals(request.getStockName(), capturedStock.getStockName());
        assertEquals(request.getInvestValue(), capturedStock.getInvestValue(), 0.0001);

        // Verify that the makeAPICall method was called once
        verify(stockService, times(1)).makeAPICall(any(Stock.class));
    }


    @AfterAll
    public static void tearDown() {
        System.out.println("After all tests have completed:");
        System.out.println("StockTest methods have run successfully.");
    }

    @AfterEach
    public void cleanUpEach() {
        System.out.println("After each test method:");
        System.out.println("Cleaning up resources or resetting state...");
    }
}
