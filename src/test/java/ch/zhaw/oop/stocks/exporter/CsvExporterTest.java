package ch.zhaw.oop.stocks.exporter;

import ch.zhaw.oop.stocks.stocks.Stock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * The type Csv exporter test.
 */
public class CsvExporterTest {
    @Mock
    private Stock mockStock;

    private CsvExporter csvExporter;

    /**
     * Sets .
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockStock = Mockito.mock(Stock.class);
        csvExporter = new CsvExporter(mockStock);
    }

    /**
     * Test export stock data successful.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testExportStockData_Successful() throws IOException {
        // Arrange
        String filename = "test.csv";

        LocalDate startDate = LocalDate.of(2023, 1, 1); // Set a valid start date
        when(mockStock.getStartDate()).thenReturn(startDate);
        when(mockStock.getEndDate()).thenReturn(LocalDate.of(2023, 1, 31)); // Set a valid end date
        when(mockStock.getStockName()).thenReturn("Test Stock");
        when(mockStock.getStartValue()).thenReturn(100.0);
        when(mockStock.getEndValue()).thenReturn(150.0);
        when(mockStock.getInvestValue()).thenReturn(200.0);
        when(mockStock.getFinalValue()).thenReturn(180.0);
        when(mockStock.getGainLossValue()).thenReturn(-20.0);

        // Act
        String result = csvExporter.exportStockData(mockStock, filename);

        // Assert
        String expectedFileUrl = "http://localhost:4200/assets/csvexport/test.csv";
        assertEquals(expectedFileUrl, result);

        // Verify that necessary methods were called
        verify(mockStock).getStartDate();
        verify(mockStock).getEndDate();
        verify(mockStock).getStockName();
        verify(mockStock).getStartValue();
        verify(mockStock).getEndValue();
        verify(mockStock).getInvestValue();
        verify(mockStock).getFinalValue();
        verify(mockStock).getGainLossValue();
    }


    /**
     * Test export stock data exception.
     */
    @Test
    public void testExportStockData_Exception() {
        // Create a null stock object
        Stock mockStock = null;

        // Create CsvExporter instance
        CsvExporter csvExporter = new CsvExporter(mockStock);

        // Assert that an IOException is thrown when exporting stock data with a null stock object
        assertThrows(IOException.class, () -> {
            csvExporter.exportStockData(mockStock, "test.csv");
        }, "Stock object is null");
    }

    /**
     * Tear down.
     */
    @AfterAll
    public static void tearDown() {
        System.out.println("After all tests have completed:");
        System.out.println("CsvExporterTest methods have run successfully.");
    }

    /**
     * Clean up each.
     */
    @AfterEach
    public void cleanUpEach() {
        System.out.println("After each test method:");
        System.out.println("Cleaning up resources or resetting state...");
    }
}
