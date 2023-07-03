package ch.zhaw.oop.stocks.stocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * FEM: Stock object class which is used to hold the stock data.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
public class StockTest {
    private Stock stock;
    // FEM: Create new stock object before each test.
    @BeforeEach
    public void setup() {
        stock = new Stock();
    }
    // FEM: Test default constructor:
    @Test
    public void testDefaultConstructor() {
        Assertions.assertEquals(LocalDate.of(1900, 1, 1), stock.getStartDate());
        Assertions.assertEquals(LocalDate.of(2999, 1, 1), stock.getEndDate());
        Assertions.assertEquals("Default", stock.getStockName());
        Assertions.assertEquals(0.0, stock.getStartValue());
        Assertions.assertEquals(0.0, stock.getEndValue());
        Assertions.assertEquals(0.0, stock.getInvestValue());
        Assertions.assertEquals(0.0, stock.getFinalValue());
        Assertions.assertEquals(0.0, stock.getGainLossValue());
    }
    // FEM: Test all getters and setters:
    @Test
    public void testSettersAndGetters() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        String stockName = "AAPL";
        double startValue = 150.0;
        double endValue = 180.0;
        double investValue = 1000.0;
        double finalValue = 1200.0;
        double gainLossValue = 200.0;

        stock.setStartDate(startDate);
        stock.setEndDate(endDate);
        stock.setStockName(stockName);
        stock.setStartValue(startValue);
        stock.setEndValue(endValue);
        stock.setInvestValue(investValue);
        stock.setFinalValue(finalValue);
        stock.setGainLossValue(gainLossValue);

        Assertions.assertEquals(startDate, stock.getStartDate());
        Assertions.assertEquals(endDate, stock.getEndDate());
        Assertions.assertEquals(stockName, stock.getStockName());
        Assertions.assertEquals(startValue, stock.getStartValue());
        Assertions.assertEquals(endValue, stock.getEndValue());
        Assertions.assertEquals(investValue, stock.getInvestValue());
        Assertions.assertEquals(finalValue, stock.getFinalValue());
        Assertions.assertEquals(gainLossValue, stock.getGainLossValue());
    }
}
