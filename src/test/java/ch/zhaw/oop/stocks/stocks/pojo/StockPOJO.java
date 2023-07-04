package ch.zhaw.oop.stocks.stocks.pojo;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * FEM: Stock object class which is used to hold the stock data.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
public class StockPOJO {
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

    public StockPOJO() {
        this.startDate = LocalDate.of(1900, 01, 01);
        this.endDate = LocalDate.of(2999, 01, 01);;
        this.stockName = "Default";
        this.startValue = 0.0;
        this.endValue = 0.0;
        this.investValue = 0.0;
        this.finalValue = 0.0;
        this.gainLossValue = 0.0;
    }

    /**
     * FEM: Create a stock object. Fills the object with placeholder (default) data.
     */
    public void setStockFromWeb(LocalDate startDate, LocalDate endDate, String stockName, double investValue) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.stockName = stockName;
        this.investValue = investValue;

        //FEM: Print-Out for better analysis:
        System.out.println("Start-Datum von Web-Eingabe: " + startDate);
        System.out.println("End-Datum von Web-Eingabe: " + endDate);
        System.out.println("Stock Name von Web-Eingabe: " + stockName);
        System.out.println("Investierter Betrag von Web-Eingabe: " + investValue);
    }
    public void setStockFromCSV(LocalDate startDate, LocalDate endDate, String stockName, double investValue) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.stockName = stockName;
        this.investValue = investValue;
    }
    public void setStockFromAPI(double startValue, double endValue) {
        this.startValue = startValue;
        this.endValue = endValue;

        // Calculate finalValue
        try {
            if (startValue != 0) {
                this.finalValue = (Math.round(investValue / startValue * endValue * 100.0)/100.0);
            } else {
                throw new ArithmeticException("Division by zero error for finalValue calculation");
            }
        } catch (ArithmeticException e) {
            // Handle the division by zero error for finalValue calculation
            this.finalValue = 0;
            System.err.println("Error calculating finalValue: " + e.getMessage());
        }

        // Calculate gainLossValue
        try {
            this.gainLossValue = (Math.round(finalValue - investValue * 100.0)/100.0);
        } catch (Exception e) {
            // Handle any other exception that may occur during the calculation of gainLossValue
            this.gainLossValue = 0;
            System.err.println("Error calculating gainLossValue: " + e.getMessage());
        }
        //FEM: Print-Out for better analysis:
        System.out.println("Start-Wert von API: " + startValue);
        System.out.println("End-Wert von API: " + endValue);
        System.out.println("Berechneter finaler Wert: " + finalValue);
        System.out.println("Berechneter Verlust oder Gewinn: " + gainLossValue);
    }
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
    // FEM: Getters and Setters
    /** FEM: Getter for the current startDate. */
    public LocalDate getStartDate() {
        return startDate;
    }
    /** FEM: Setter for the current startDate. */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    /** FEM: Getter for the current endDate.  */
    public LocalDate getEndDate() {
        return endDate;
    }
    /** FEM: Setter for the current endDate. */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    /** FEM: Getter for the current stockName. */
    public String getStockName() {
        return stockName;
    }
    /** FEM: Setter for the current stockName. */
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
    /** FEM: Getter for the current startValue. */
    public double getStartValue() {
        return startValue;
    }
    /** FEM: Setter for the current startValue. */
    public void setStartValue(double startValue) {
        this.startValue = startValue;
    }
    /** FEM: Getter for the current endValue. */
    public double getEndValue() {
        return endValue;
    }
    /** FEM: Setter for the current endValue. */
    public void setEndValue(double endValue) {
        this.endValue = endValue;
    }
    /** FEM: Getter for the current investValue. */
    public double getInvestValue() {
        return investValue;
    }
    /** FEM: Setter for the current investValue.   */
    public void setInvestValue(double investValue) {
        this.investValue = investValue;
    }
    /** FEM: Getter for the current finalValue. */
    public double getFinalValue() {
        return finalValue;
    }
    /** FEM: Setter for the current finalValue.   */
    public void setFinalValue(double finalValue) {this.finalValue = finalValue;
    }
    /** FEM: Getter for the current gainLossValue. */
    public double getGainLossValue() {
        return gainLossValue;
    }
    /** FEM: Setter for the current gainLossValue.   */
    public void setGainLossValue(double gainLossValue) {
        this.gainLossValue = gainLossValue;
    }
    /** FEM: Default toString Method. Converts all object data to String.
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