package ch.zhaw.oop.stocks.api.pojo;

import java.time.LocalDate;

/**
 * The type Api stock value pojo.
 */
public class ApiStockValuePOJO {

    private LocalDate datetime;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;

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

    /**
     * Sets datetime.
     *
     * @param datetime the datetime
     */
// ADR: Added setters for testing purposes
    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }

    /**
     * Sets close.
     *
     * @param close the close
     */
    public void setClose(double close) {
        this.close = close;
    }
}