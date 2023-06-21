package ch.zhaw.oop.stocks.pojo;

import java.time.LocalDate;

public class StockValuePOJO {

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
