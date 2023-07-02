package ch.zhaw.oop.stocks.api.pojo;

import java.time.LocalDate;

public class ApiStockEarliestTimestampPOJO {
    private LocalDate datetime;

    public LocalDate getDatetime() {
        return datetime;
    }
    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }
}
