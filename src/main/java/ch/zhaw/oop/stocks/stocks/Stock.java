package ch.zhaw.oop.stocks.stocks;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
/**
 * FEM: Stock object class which is used to hold the stock data.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@Component
public class Stock {
    /**
     * Constructor for stock class objects.
     * @param startDate, LocalDate, Start Date of the stock period
     * @param endDate, LocalDate,  End Date of the stock period
     * @param stockName, String, Unique Name Identifier of the stock, used for API Calls
     * @param startValue, Double, Received Stock value at start date (close)
     * @param endValue, Double, Received Stock value at end date (close)
     * @param investValue, double, Received investment Value
     * @param finalValue, double, received calculated final value of stock portfolio.
     * @param gainLossValue, double, received calculated difference (gain or loss) of stock portfolio.
     */
    private LocalDate startDate, endDate;
    private String stockName;
    private double startValue, endValue, investValue, finalValue, gainLossValue;

    // Getters and setters

    /**
     * FEM: Create a stock object. Fills the object with placeholder (default) data.
     */

    public void createStockObject() {
        // Placeholder method to create a stock object
        startDate = LocalDate.of(0000, 0, 0);
        endDate = LocalDate.of(0000, 0, 0);
        stockName = "PLACEHOLDER";
        startValue = 0.0;
        endValue = 0.0;
        investValue = 0.0;
        finalValue = 0.0;
        gainLossValue = 0.0;
    }
    // Getters and Setters
    /** Method: Getter for the current startDate. */
    public LocalDate getStartDate() {
        return startDate;
    }
    /** Method: Setter for the current startDate. */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    /** Method: Getter for the current endDate.  */
    public LocalDate getEndDate() {
        return endDate;
    }
    /** Method: Setter for the current endDate. */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    /** Method: Getter for the current stockName. */
    public String getStockName() {
        return stockName;
    }
    /** Method: Setter for the current stockName. */
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
    /** Method: Getter for the current startValue. */
    public double getStartValue() {
        return startValue;
    }
    /** Method: Setter for the current startValue. */
    public void setStartValue(double startValue) {
        this.startValue = startValue;
    }
    /** Method: Getter for the current endValue. */
    public double getEndValue() {
        return endValue;
    }
    /** Method: Setter for the current endValue. */
    public void setEndValue(double endValue) {
        this.endValue = endValue;
    }
    /** Method: Getter for the current investValue. */
    public double getInvestValue() {
        return investValue;
    }
    /** Method: Setter for the current investValue.   */
    public void setInvestValue(double investValue) {
        this.investValue = investValue;
    }
    /** Method: Getter for the current finalValue. */
    public double getFinalValue() {
        return finalValue;
    }
    /** Method: Setter for the current finalValue.   */
    public void setFinalValue(double finalValue) {this.finalValue = finalValue;
    }
    /** Method: Getter for the current gainLossValue. */
    public double getGainLossValue() {
        return gainLossValue;
    }
    /** Method: Setter for the current gainLossValue.   */
    public void setGainLossValue(double gainLossValue) {
        this.gainLossValue = gainLossValue;
    }
    /** Method: Default toString Method. Converts all object data to String.
     * @return Stock */
    @Override
    public String toString() {
        return "Stock{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", stockName='" + stockName + '\'' +
                ", startValue=" + startValue +
                ", endValue=" + endValue +
                ", investValue=" + investValue +
                ", finalValue=" + finalValue +
                ", gainLossValue=" + gainLossValue +
                '}';
    }
}
