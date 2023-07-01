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
        System.out.println(stock.getStockName());
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"file.pdf\"")
                .body("Platzhalter für downloadLink");
    }
}
