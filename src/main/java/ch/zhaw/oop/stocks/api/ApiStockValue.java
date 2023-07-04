package ch.zhaw.oop.stocks.api;

import java.time.LocalDate;

/**
 * <h1>StockValue</h1>
 * <p>This class represents the model for data of the stock of a specific day</p>
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
public class ApiStockValue {

    private LocalDate datetime;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;

    public ApiStockValue() { }
    public ApiStockValue(LocalDate datetime, double open, double high, double low, double close, double volume) {
        this.datetime = datetime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

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
