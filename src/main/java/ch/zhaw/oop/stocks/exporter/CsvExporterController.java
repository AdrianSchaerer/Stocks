package ch.zhaw.oop.stocks.exporter;

import ch.zhaw.oop.stocks.stocks.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
/**
 * <h1>CsvExporterController</h1>
 * <p>FEM: CSV Exporter controller class which is used to create new Exporters.</p>
 * <p>Acts as a controller for Angular frontend.</p>
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */

@RestController
@RequestMapping("/exporter") // FEM: Base URL for all endpoints of this class.
public class CsvExporterController {

    @Autowired
    private CsvExporter csvExporter;
    @Autowired
    private Stock stock;
    /**
     * FEM: Exports stock data to a CSV file and returns the file URL as a response entity.
     *
     * @return A ResponseEntity containing the file URL if the export is successful,
     * or an error response with appropriate status code if there is an exception.
     */
    @PostMapping("/export") // FEM: Endpoint mapping for CSV Export method. > URL /exporter/export
    public ResponseEntity<String> exportStockDataToCsv(){
        System.out.println("Daten, zu Beginn der Exportfunktion im Stock-Objekt:"+stock.toString());

        String filename = stock.getStartDate() + "_" + stock.getEndDate() + "_" + stock.getStockName() + ".csv";

        try {
            // Export stock data to CSV file
            String fileUrl = csvExporter.exportStockData(stock, filename);

            // Return the file URL to the web frontend
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            // Handle any exceptions and return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error exporting stock data to CSV");
        }
    }
    public CsvExporter getCsvExporter() {
        return csvExporter;
    }
    public void setCsvExporter(CsvExporter csvExporter) {
        this.csvExporter = csvExporter;
    }
    public Stock getStock() {
        return stock;
    }
    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
