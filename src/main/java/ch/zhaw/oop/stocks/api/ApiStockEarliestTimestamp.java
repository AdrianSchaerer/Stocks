package ch.zhaw.oop.stocks.api;

import java.time.LocalDate;

/**
 * <h1>StockEarliestTimestamp</h1>
 * <p>StockEarliestTimestamp is used in StockService for the method earliest_timestamp.</p>
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
public class ApiStockEarliestTimestamp {

    private LocalDate datetime;
    private int unix_time;

    public LocalDate getDatetime() {
        return datetime;
    }

    public int getUnix_time() {
        return unix_time;
    }

}
