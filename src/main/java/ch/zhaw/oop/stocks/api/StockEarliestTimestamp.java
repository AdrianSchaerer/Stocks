package ch.zhaw.oop.stocks.api;

import java.time.LocalDate;

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
