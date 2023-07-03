package ch.zhaw.oop.stocks.exporter;

import ch.zhaw.oop.stocks.stocks.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CsvExporterTest {
    private CsvExporter csvExporter;
    private Stock stock;

    @BeforeEach
    public void setup() {
        stock = new Stock();
        csvExporter = new CsvExporter(stock);
    }

    @Test
    public void testExportStockData() throws IOException {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        String stockName = "AAPL";
        double startValue = 100.0;
        double endValue = 150.0;
        double investValue = 1000.0;
        double finalValue = 1100.0;
        double gainLossValue = 100.0;

        stock.setStartDate(startDate);
        stock.setEndDate(endDate);
        stock.setStockName(stockName);
        stock.setStartValue(startValue);
        stock.setEndValue(endValue);
        stock.setInvestValue(investValue);
        stock.setFinalValue(finalValue);
        stock.setGainLossValue(gainLossValue);

        String filename = "test.csv";
        String filePath = "src/frontend/form/src/assets/csvexport/" + filename;

        assertTrue(Files.exists(Path.of(filePath)));

        File file = new File(filePath);
        assertTrue(file.isFile());

        String[] lines = Files.readAllLines(Path.of(filePath)).toArray(String[]::new);
        assertEquals(2, lines.length); // Header + Data

        String header = lines[0];
        assertEquals("startDate,endDate,stockName,startValue,endValue,investValue,finalValue,gainLossValue", header);

        String dataRow = lines[1];
        String expectedDataRow = startDate + "," + endDate + "," + stockName + "," +
                startValue + "," + endValue + "," + investValue + "," +
                finalValue + "," + gainLossValue;
        assertEquals(expectedDataRow, dataRow);

        // Clean up the generated file after the test
        Files.deleteIfExists(Path.of(filePath));
    }
}
