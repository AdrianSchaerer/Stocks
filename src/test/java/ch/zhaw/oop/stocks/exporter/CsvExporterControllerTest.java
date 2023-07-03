package ch.zhaw.oop.stocks.exporter;

import ch.zhaw.oop.stocks.stocks.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CsvExporterControllerTest {
    private CsvExporterController csvExporterController;
    private CsvExporter csvExporter;
    private Stock stock;

    @BeforeEach
    public void setup() {
        csvExporter = mock(CsvExporter.class);
        stock = mock(Stock.class);
        csvExporterController = new CsvExporterController();
    }

    @Test
    public void testExportStockDataToCsv_Successful() throws IOException {
        String filename = "2023-01-01_2023-12-31_AAPL.csv";
        String fileUrl = "http://localhost:4200/assets/csvexport/" + filename;

        when(stock.getStartDate()).thenReturn(LocalDate.of(2023, 1, 1));
        when(stock.getEndDate()).thenReturn(LocalDate.of(2023, 12, 31));
        when(stock.getStockName()).thenReturn("AAPL");
        when(csvExporter.exportStockData(stock, filename)).thenReturn(fileUrl);

        ResponseEntity<String> responseEntity = csvExporterController.exportStockDataToCsv();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(fileUrl, responseEntity.getBody());
        verify(csvExporter).exportStockData(stock, filename);
        verifyNoMoreInteractions(csvExporter);
    }

    @Test
    public void testExportStockDataToCsv_Exception() throws IOException {
        String errorMessage = "Error exporting stock data to CSV";

        when(csvExporter.exportStockData(stock, anyString())).thenThrow(new IOException(errorMessage));

        ResponseEntity<String> responseEntity = csvExporterController.exportStockDataToCsv();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
        verify(csvExporter).exportStockData(stock, anyString());
        verifyNoMoreInteractions(csvExporter);
    }
}
