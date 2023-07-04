package ch.zhaw.oop.stocks.api.pojo;

import java.time.LocalDate;

/**
 * The type Api stock earliest timestamp pojo.
 */
public class ApiStockEarliestTimestampPOJO {
    private LocalDate datetime;

    /**
     * Gets datetime.
     *
     * @return the datetime
     */
    public LocalDate getDatetime() {
        return datetime;
    }

    /**
     * Sets datetime.
     *
     * @param datetime the datetime
     */
    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }
}