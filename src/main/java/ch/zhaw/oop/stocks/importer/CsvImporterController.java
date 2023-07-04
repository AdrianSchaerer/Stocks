package ch.zhaw.oop.stocks.importer;

import ch.zhaw.oop.stocks.stocks.Stock;
import ch.zhaw.oop.stocks.stocks.StockRequest;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>FEM: CSV Importer controller class which is used to create new Importers</p>
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@RestController
public class CsvImporterController {

    /**
     *
     */
    private final CsvImporter csvImporter;

    /**
     * Instantiates a new Csv importer controller.
     *
     * @param csvImporter the csv importer
     */
    @Autowired
    public CsvImporterController(CsvImporter csvImporter) {
        this.csvImporter = csvImporter;
    }

    /**
     * Import csv response entity.
     *
     * @param file the file
     * @return the response entity
     */
    @PostMapping("/importer")
    public ResponseEntity<Stock> importCsv(@RequestPart("file") MultipartFile file) {
        try {
            Stock importedStock = csvImporter.importData(file);
            return ResponseEntity.ok(importedStock);
        } catch (Exception e) {
            // Handle any exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}