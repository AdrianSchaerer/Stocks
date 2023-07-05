package ch.zhaw.oop.stocks.stocks;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * <p>FEM: Stock object class which is used to hold the stock data.</p>
 * <p>This class represents a stock with its properties and methods for setting and retrieving data.</p>
 *
 * This class is work in progress and currently not in use.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@Component
public class Stock {
    private LocalDate startDate;
    private LocalDate endDate;
    private String stockName;
    private double startValue;
    private double endValue;
    private double investValue;
    private double finalValue;
    private double gainLossValue;

    /**
     * FEM: Constructor for creating a Stock object with default values.
     */
    public Stock() {
        this.startDate = LocalDate.of(1900, 1, 1);
        this.endDate = LocalDate.of(2999, 1, 1);
        this.stockName = "Default";
        this.startValue = 0.0;
        this.endValue = 0.0;
        this.investValue = 0.0;
        this.finalValue = 0.0;
        this.gainLossValue = 0.0;
    }

    /**
     * FEM: Sets the stock data from a web input.
     *
     * @param startDate   The start date of the stock period.
     * @param endDate     The end date of the stock period.
     * @param stockName   The unique name identifier of the stock used for API calls.
     * @param investValue The received investment value.
     */
    public void setStockFromWeb(LocalDate startDate, LocalDate endDate, String stockName, double investValue) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.stockName = stockName;
        this.investValue = investValue;

        // FEM: Print for better analysis
        System.out.println("Start-Datum von Web-Eingabe: " + startDate);
        System.out.println("End-Datum von Web-Eingabe: " + endDate);
        System.out.println("Stock Name von Web-Eingabe: " + stockName);
        System.out.println("Investierter Betrag von Web-Eingabe: " + investValue);
    }

    /**
     * FEM: Sets the stock data from a CSV file.
     *
     * Currently not used (for importer-purposes)
     *
     * @param startDate   The start date of the stock period.
     * @param endDate     The end date of the stock period.
     * @param stockName   The unique name identifier of the stock.
     * @param investValue The received investment value.
     */
    public void setStockFromCSV(LocalDate startDate, LocalDate endDate, String stockName, double investValue) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.stockName = stockName;
        this.investValue = investValue;
    }

    /**
     * FEM: Sets the stock data from an API response.
     *
     * @param startValue The received stock value at the start date.
     * @param endValue   The received stock value at the end date.
     */
    public void setStockFromAPI(double startValue, double endValue) {
        this.startValue = startValue;
        this.endValue = endValue;

        // FEM: Calculate finalValue and rounds the result. Very basic - may include a more sophisticated calculation method:
        try {
            if (startValue != 0) {
                this.finalValue = Math.round(investValue / startValue * endValue * 100.0) / 100.0;
            } else {
                throw new ArithmeticException("Division by zero error for finalValue calculation");
            }
        } catch (ArithmeticException e) {
            // Handle the division by zero error for finalValue calculation
            this.finalValue = 0;
            System.err.println("Error calculating finalValue: " + e.getMessage());
        }

        // FEM: Calculate gainLossValue and rounds the result. Very basic - may include a more sophisticated calculation method:
        try {
            this.gainLossValue = Math.round((finalValue - investValue) * 100.0) / 100.0;
        } catch (Exception e) {
            // FEM: Handle any other exception that may occur during the calculation of gainLossValue
            this.gainLossValue = 0;
            System.err.println("Error calculating gainLossValue: " + e.getMessage());
        }

        // FEM: Print for better analysis
        System.out.println("Start-Wert von API: " + startValue);
        System.out.println("End-Wert von API: " + endValue);
        System.out.println("Berechneter finaler Wert: " + finalValue);
        System.out.println("Berechneter Verlust oder Gewinn: " + gainLossValue);
    }

    /**
     * FEM: Creates a stock object with placeholder data.
     *
     * Currently not used, only for testing-purposes.
     */
    public void createStockObject() {
        startDate = LocalDate.of(1900, 1, 1);
        endDate = LocalDate.of(1900, 1, 1);
        stockName = "PLACEHOLDER";
        startValue = 0.0;
        endValue = 0.0;
        investValue = 0.0;
        finalValue = 0.0;
        gainLossValue = 0.0;
    }

    // FEM: Getters and Setters

    /**
     * FEM: Get the start date of the stock.
     *
     * @return The start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * FEM: Set the start date of the stock.
     *
     * @param startDate The start date to set.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * FEM: Get the end date of the stock.
     *
     * @return The end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * FEM: Set the end date of the stock.
     *
     * @param endDate The end date to set.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * FEM: Get the stock name.
     *
     * @return The stock name.
     */
    public String getStockName() {
        return stockName;
    }

    /**
     * FEM: Set the stock name.
     *
     * @param stockName The stock name to set.
     */
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    /**
     * FEM: Get the stock's start value.
     *
     * @return The start value.
     */
    public double getStartValue() {
        return startValue;
    }

    /**
     * FEM: Set the stock's start value.
     *
     * @param startValue The start value to set.
     */
    public void setStartValue(double startValue) {
        this.startValue = startValue;
    }

    /**
     * FEM: Get the stock's end value.
     *
     * @return The end value.
     */
    public double getEndValue() {
        return endValue;
    }

    /**
     * FEM: Set the stock's end value.
     *
     * @param endValue The end value to set.
     */
    public void setEndValue(double endValue) {
        this.endValue = endValue;
    }

    /**
     * FEM: Get the investment value.
     *
     * @return The investment value.
     */
    public double getInvestValue() {
        return investValue;
    }

    /**
     * FEM: Set the investment value.
     *
     * @param investValue The investment value to set.
     */
    public void setInvestValue(double investValue) {
        this.investValue = investValue;
    }

    /**
     * FEM: Get the final value of the stock portfolio.
     *
     * @return The final value.
     */
    public double getFinalValue() {
        return finalValue;
    }

    /**
     * FEM: Set the final value of the stock portfolio.
     *
     * @param finalValue The final value to set.
     */
    public void setFinalValue(double finalValue) {
        this.finalValue = finalValue;
    }

    /**
     * FEM: Get the gain/loss value of the stock portfolio.
     *
     * @return The gain/loss value.
     */
    public double getGainLossValue() {
        return gainLossValue;
    }

    /**
     * FEM: Set the gain/loss value of the stock portfolio.
     *
     * @param gainLossValue The gain/loss value to set.
     */
    public void setGainLossValue(double gainLossValue) {
        this.gainLossValue = gainLossValue;
    }

    /**
     * FEM: Returns a string representation of the Stock object.
     *
     * @return The string representation of the Stock object.
     */
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
