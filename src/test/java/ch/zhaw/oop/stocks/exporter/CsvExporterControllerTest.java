package ch.zhaw.oop.stocks.exporter;

import ch.zhaw.oop.stocks.stocks.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * FEM: The Csv exporter controller test.
 */
class CsvExporterControllerTest {

    @Mock
    private CsvExporter csvExporter;

    @Mock
    private Stock stock;

    /**
     * Private CsvExporterController
     */
    private CsvExporterController csvExporterController;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        csvExporterController = new CsvExporterController();
        csvExporterController.setCsvExporter(csvExporter);
        when(stock.toString()).thenReturn("Mocked Stock");
        csvExporterController.setStock(stock);
    }

    /**
     * Export stock data to csv export successful returns file url.
     *
     * @throws IOException the io exception
     */
    @Test
    public void exportStockDataToCsv_ExportSuccessful_ReturnsFileUrl() throws IOException {
        // Arrange
        // No need to initialize stock here since it's already mocked

        // Act
        csvExporterController.exportStockDataToCsv();

        // Assert
        verify(csvExporter).exportStockData(eq(stock), anyString());
    }

    /**
     * Export stock data to csv export throws io exception returns internal server error.
     *
     * @throws IOException the io exception
     */
    @Test
    void exportStockDataToCsv_ExportThrowsIOException_ReturnsInternalServerError() throws IOException {
        // Arrange
        String expectedErrorResponse = "Error exporting stock data to CSV";
        when(csvExporter.exportStockData(any(Stock.class), anyString())).thenThrow(IOException.class);

        // Act
        ResponseEntity<String> response = csvExporterController.exportStockDataToCsv();

        // Assert
        verify(csvExporter).exportStockData(any(Stock.class), anyString());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(expectedErrorResponse, response.getBody());
    }
}
