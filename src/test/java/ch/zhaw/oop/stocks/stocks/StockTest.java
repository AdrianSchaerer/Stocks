package ch.zhaw.oop.stocks.stocks;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;

import java.time.LocalDate;

/**
 * FEM: The Stock test.
 */
@SpringBootTest
public class StockTest {
    @Mock
    private static Stock mockStock;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setup() {
        mockStock = new Stock();
    }

    /**
     * Test default constructor.
     */
    @Test
    public void testDefaultConstructor() {
        // Arrange
        LocalDate expectedStartDate = LocalDate.of(1900, 1, 1);
        LocalDate expectedEndDate = LocalDate.of(2999, 1, 1);
        String expectedStockName = "Default";
        double expectedStartValue = 0.0;
        double expectedEndValue = 0.0;
        double expectedInvestValue = 0.0;
        double expectedFinalValue = 0.0;
        double expectedGainLossValue = 0.0;

        // Assert
        Assertions.assertEquals(expectedStartDate, mockStock.getStartDate());
        Assertions.assertEquals(expectedEndDate, mockStock.getEndDate());
        Assertions.assertEquals(expectedStockName, mockStock.getStockName());
        Assertions.assertEquals(expectedStartValue, mockStock.getStartValue());
        Assertions.assertEquals(expectedEndValue, mockStock.getEndValue());
        Assertions.assertEquals(expectedInvestValue, mockStock.getInvestValue());
        Assertions.assertEquals(expectedFinalValue, mockStock.getFinalValue());
        Assertions.assertEquals(expectedGainLossValue, mockStock.getGainLossValue());
    }

    /**
     * Test setters and getters.
     */
    @Test
    public void testSettersAndGetters() {
        // Arrange
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        String stockName = "AAPL";
        double startValue = 150.0;
        double endValue = 180.0;
        double investValue = 1000.0;
        double finalValue = 1200.0;
        double gainLossValue = 200.0;

        // Act
        mockStock.setStartDate(startDate);
        mockStock.setEndDate(endDate);
        mockStock.setStockName(stockName);
        mockStock.setStartValue(startValue);
        mockStock.setEndValue(endValue);
        mockStock.setInvestValue(investValue);
        mockStock.setFinalValue(finalValue);
        mockStock.setGainLossValue(gainLossValue);

        // Assert
        Assertions.assertEquals(startDate, mockStock.getStartDate());
        Assertions.assertEquals(endDate, mockStock.getEndDate());
        Assertions.assertEquals(stockName, mockStock.getStockName());
        Assertions.assertEquals(startValue, mockStock.getStartValue());
        Assertions.assertEquals(endValue, mockStock.getEndValue());
        Assertions.assertEquals(investValue, mockStock.getInvestValue());
        Assertions.assertEquals(finalValue, mockStock.getFinalValue());
        Assertions.assertEquals(gainLossValue, mockStock.getGainLossValue());
    }

    /**
     * Tear down.
     */
    @AfterAll
    public static void tearDown() {
        System.out.println("After all tests have completed:");
        System.out.println("Current Data in Stock-Object: " + mockStock.toString());
        System.out.println("StockTest methods have run successfully.");
    }

    /**
     * Clean up each.
     */
    @AfterEach
    public void cleanUpEach() {
        System.out.println("After each test method:");
        System.out.println("Cleaning up resources or resetting state...");
    }
}
