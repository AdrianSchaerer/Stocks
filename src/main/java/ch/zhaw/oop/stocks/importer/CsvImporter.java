package ch.zhaw.oop.stocks.importer;
import ch.zhaw.oop.stocks.stocks.Stock;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
/**
 * FEM: This class is used to perform basic CSV importer tasks.
 * Imports data from CSV file located in /resources/csvimport/
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@Component
public class CsvImporter {

    private final Stock stock;

    @Value("src/main/resources/csvimport")
    private String csvImportDirectory;

    /** This JavaDoc comment should describe the constructor. */
    @Autowired
    public CsvImporter(Stock stock) {
        this.stock = stock;
    }

    public void importCSV(String filename) {
        // Create the file path
        String filePath = csvImportDirectory + "/" + filename;

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            // Read the CSV data (assuming one single row)
            String[] rowData = reader.readNext();

            // Extract the data from the CSV row
            LocalDate startDate = LocalDate.parse(rowData[0]);
            LocalDate endDate = LocalDate.parse(rowData[1]);
            String stockName = rowData[2];
            double investValue = Double.parseDouble(rowData[3]);

            // Set the data in the Stock object
            stock.setStartDate(startDate);
            stock.setEndDate(endDate);
            stock.setStockName(stockName);
            stock.setInvestValue(investValue);
        } catch (IOException e) {
            // Handle the exception here or propagate it to the caller
            e.printStackTrace();
        } catch (CsvValidationException e) {
            // Handle the exception here or propagate it to the caller
            throw new RuntimeException(e);
        }
    }
}
