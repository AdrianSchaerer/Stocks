package ch.zhaw.oop.stocks;

import java.util.Arrays;
import java.util.List;
/**
 * FEM: Stores the stock details.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
public class Stock {
    /* 	FEM: Stock data definition
     *	Defines the stock data values.
     *  Important: Column headers in the CSV match the field names.
     * 	Usage: Overall stock data handling and CSVexporter */
    private String startDate, endDate, stockName;
    private double startValue, endValue, gain;
    /**
     * Constructor for stock class objects.
     * @param startDate     Start Date of the stock period
     * @param endDate       End Date of the stock period
     * @param stockName     Unique Name Identifier of the stock, used for API Calls
     * @param startValue    Received Stock value at start date (close)
     * @param endValue      Received Stock value at end date (close)
     * @param gain          Calculated gain or loss during predefined time period
     */
    public Stock(String startDate, String endDate, String stockName, double startValue, double endValue, double gain) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.stockName = stockName;
        this.startValue = startValue;
        this.endValue = endValue;
        this.gain = gain;
    }
    /**
     * FEM: Get stock details as Array List collection
     *
     * @author Adrian Schärer, Dominic Troll, Manuel Ferretti
     * @version 0.1
     */
    public List<Object> getStockDetails()
    {
        System.out.println("The following parameters are set:");
        System.out.println("Start date: "+startDate);
        System.out.println("End date: "+endDate);
        System.out.println("Stock name: "+stockName);
        System.out.println("Start value from Database: "+startValue);
        System.out.println("End value from Database: "+endValue);
        System.out.println("Calculated loss or gain value: "+gain);
        System.out.println("Parameters are exported as array...");
        return Arrays.asList(startDate, endDate, stockName, startValue,endValue,gain);
    }
    // Getters and setters
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getStartValue() {
        return startValue;
    }

    public void setStartValue(double startValue) {
        this.startValue = startValue;
    }

    public double getEndValue() {
        return endValue;
    }

    public void setEndValue(double endValue) {
        this.endValue = endValue;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }
}
