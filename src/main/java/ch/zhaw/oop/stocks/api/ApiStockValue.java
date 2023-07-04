package ch.zhaw.oop.stocks.api;

import java.time.LocalDate;

/**
 * <p>This class represents the model for data of the stock of a specific day</p>
 *
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

    /**
     * Instantiates a new Api stock value.
     */
    public ApiStockValue() { }

    /**
     * Instantiates a new Api stock value.
     *
     * @param datetime the datetime
     * @param open     the open
     * @param high     the high
     * @param low      the low
     * @param close    the close
     * @param volume   the volume
     */
    public ApiStockValue(LocalDate datetime, double open, double high, double low, double close, double volume) {
        this.datetime = datetime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    /**
     * Gets datetime.
     *
     * @return the datetime
     */
    public LocalDate getDatetime() {
        return datetime;
    }

    /**
     * Gets open.
     *
     * @return the open
     */
    public double getOpen() {
        return open;
    }

    /**
     * Gets high.
     *
     * @return the high
     */
    public double getHigh() {
        return high;
    }

    /**
     * Gets low.
     *
     * @return the low
     */
    public double getLow() {
        return low;
    }

    /**
     * Gets close.
     *
     * @return the close
     */
    public double getClose() {
        return close;
    }

    /**
     * Gets volume.
     *
     * @return the volume
     */
    public double getVolume() {
        return volume;
    }
}
