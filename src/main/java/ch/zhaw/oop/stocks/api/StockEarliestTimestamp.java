package ch.zhaw.oop.stocks.api;

import java.time.LocalDate;

/**
 * <h1>StockEarliestTimestamp</h1>
 * StockEarliestTimestamp is used in StockService for the method earliest_timestamp.
 * @author      Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version     1.0
 * @since       2023-06-23
 */
public class StockEarliestTimestamp {

    private LocalDate datetime;
    private int unix_time;

    public LocalDate getDatetime() {
        return datetime;
    }

    public int getUnix_time() {
        return unix_time;
    }

}
