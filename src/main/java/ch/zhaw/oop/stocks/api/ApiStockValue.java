package ch.zhaw.oop.stocks.api;

import java.time.LocalDate;

/**
 * <h1>StockValue</h1>
 * <p>This class represents the model for data of the stock of a specific day</p>
 * @author      Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version     1.0
 * @since       2023-06-23
 */
public class ApiStockValue {

    private LocalDate datetime;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;

    public LocalDate getDatetime() {
        return datetime;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public double getVolume() {
        return volume;
    }
}
