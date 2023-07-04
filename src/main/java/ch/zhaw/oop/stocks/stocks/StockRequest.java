package ch.zhaw.oop.stocks.stocks;
import java.time.LocalDate;

/**
 * <p>FEM: Represents a stock request containing the necessary data to create a stock.</p>
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
public class StockRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private String stockName;
    private double investValue;

    /**
     * FEM: Get the start date of the stock request.
     *
     * @return The start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * FEM: Set the start date of the stock request.
     *
     * @param startDate The start date to set.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * FEM: Get the end date of the stock request.
     *
     * @return The end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * FEM: Set the end date of the stock request.
     *
     * @param endDate The end date to set.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * FEM: Get the stock name of the stock request.
     *
     * @return The stock name.
     */
    public String getStockName() {
        return stockName;
    }

    /**
     * FEM: Set the stock name of the stock request.
     *
     * @param stockName The stock name to set.
     */
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    /**
     * FEM: Get the investment value of the stock request.
     *
     * @return The investment value.
     */
    public double getInvestValue() {
        return investValue;
    }

    /**
     * FEM: Set the investment value of the stock request.
     *
     * @param investValue The investment value to set.
     */
    public void setInvestValue(double investValue) {
        this.investValue = investValue;
    }
}
