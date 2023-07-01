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


@RestController
@RequestMapping("/exporter")
public class CsvExporterController {

    @Autowired
    private CsvExporter csvExporter;
    @Autowired
    private Stock stock;
    @PostMapping("/export")
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
}
