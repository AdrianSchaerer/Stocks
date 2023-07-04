package ch.zhaw.oop.stocks.api;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>StockEarliestTimestamp is used in StockService for the method earliest_timestamp.</p>
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
public class ApiStockEarliestTimestamp {

    private LocalDate datetime;

    /**
     * Gets datetime.
     *
     * @return the datetime
     */
    public LocalDate getDatetime() {
        return datetime;
    }

}
