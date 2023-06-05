package ch.zhaw.oop.stocks;
import com.opencsv.bean.CsvBindByName;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

/**
 * FEM: Stores the stock details.
 * Also provides the loss/gain for the defined period and exports the data to a CSV file.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
public class Stock {
    /* 	FEM: Stock data definition
     *	Defines the stock data values.
     *  Important: Column headers in the CSV match the field names.
     * 	Usage: Overall stock data handling and CSVexporter */
    public String startDate, endDate, stockName;
    public double startValue, endValue, gain;

    /**
     * Constructor for stock class objects.
     * @param startDate     Start Date of the stock period
     * @param endDate       End Date of the stock period
     * @param stockName     Unique Name Identifier of the stock, used for API Calls
     * @param startValue    Received Stock value at start date (close)
     * @param endValue      Received Stock value at end date (close)
     * @param gain          Calculated gain or loss during predefined time period
     */
    public Stock(String startDate, String endDate, String stockName, float startValue, float endValue, float gain)
    {
        super();
        setStockDetails(startDate, endDate, stockName, startValue, endValue, gain);
    }

    /**
     * Define
     * @param startDate     Start Date of the stock period
     * @param endDate       End Date of the stock period
     * @param stockName     Unique Name Identifier of the stock, used for API Calls
     * @param startValue    Received Stock value at start date (close)
     * @param endValue      Received Stock value at end date (close)
     * @param gain          Calculated gain or loss during predefined time period
     */
    private void setStockDetails(String startDate, String endDate, String stockName, float startValue, float endValue, float gain)
    {
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
    @Override
    public String toString()
    {
        return "Stock [startDate=" + startDate
                + ", endDate=" + endDate
                + ", stockName=" + stockName
                + ", startValue=" + startValue
                + ", endValue=" + endValue
                + ", gain=" + gain
                + "]";
    }

}
