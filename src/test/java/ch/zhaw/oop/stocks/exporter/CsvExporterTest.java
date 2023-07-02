package ch.zhaw.oop.stocks.exporter;

import ch.zhaw.oop.stocks.exporter.pojo.CsvExporterPOJO;
import ch.zhaw.oop.stocks.stocks.pojo.StockPOJO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CsvExporterTest {

    StockPOJO stockPOJO = new StockPOJO();
    CsvExporterPOJO csvExporterPOJO = new CsvExporterPOJO(stockPOJO);
    final String filename = stockPOJO.getStartDate() + "_" + stockPOJO.getEndDate() + "_" + stockPOJO.getStockName() + ".csv";
    final String filenameWrong = stockPOJO.getStartDate() + "" + stockPOJO.getEndDate() + "_" + stockPOJO.getStockName() + ".csv";

    @BeforeEach
    void setUp() {
        stockPOJO.setStartDate(LocalDate.of(2021, 1, 1));
        stockPOJO.setEndDate(LocalDate.of(2021, 1, 8));
        stockPOJO.setStockName("AAPL");
        stockPOJO.setStartValue(120);
        stockPOJO.setEndValue(130);
        stockPOJO.setInvestValue(1000);
        stockPOJO.setFinalValue(1200);
        stockPOJO.setGainLossValue(200);
    }
    @Test
    void exportStockDataTestFileURL() throws IOException {
        assertEquals("src/test/java/ch/zhaw/oop/stocks/exporter/csv/1900-01-01_2999-01-01_Default.csv",csvExporterPOJO.exportStockData(stockPOJO, filename));
    }

    @Test
    void exportStockDataTestWrongFileURL() throws IOException {
        assertNotEquals("src/test/java/ch/zhaw/oop/stocks/exporter/csv/1900-01-01_2999-01-01_Default.csv",csvExporterPOJO.exportStockData(stockPOJO, filenameWrong));
    }
}