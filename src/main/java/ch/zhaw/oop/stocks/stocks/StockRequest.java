package ch.zhaw.oop.stocks.stocks;

import java.time.LocalDate;

public class StockRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private String stockName;
    private double investValue;

    // Getters and setters for the properties
    // ...

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getInvestValue() {
        return investValue;
    }

    public void setInvestValue(double investValue) {
        this.investValue = investValue;
    }
}