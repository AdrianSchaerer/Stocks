package ch.zhaw.oop.stocks.exporter;

import ch.zhaw.oop.stocks.stocks.Stock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FEM: This class is used to perform basic CSV exporter tasks.
 * Exports data from Stock object as CSV file to /resources/csvexport/
 * Also generates an unique file name based on search query.
 * Files are overwritten if the same values are used.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@Component
public class CsvExporter {

    private final Stock stock;

    @Value("src/main/resources/csvexport")
    private String csvExportDirectory;
    /** FEM: Constructor for the CsvExporter
     *  Autoinjects stock when CsvExporter instance is created.
     *  Provides proper initialization with required stock dependency.
     */
    @Autowired
    public CsvExporter(Stock stock) {
        this.stock = stock;
    }
    /** FEM: CSV Exporter method
     *  Gets the stock data and writes it to a CSV file.
     *  @return CSV File path (String)
     */
    public String exportToCSV() throws Exception {
        // Prepare the CSV file name
        try {
            String fileName = stock.getStartDate() + "_" +
                    stock.getEndDate() + "_" +
                    stock.getStockName() + "_" +
                    stock.getInvestValue() + ".csv";

            // Create the file path
            Path filePath = Path.of(csvExportDirectory, fileName);

            // Create a CSVWriter
            try (CSVWriter writer = new CSVWriter(new FileWriter(filePath.toFile()))) {
                // Write header
                writer.writeNext(new String[]{"Start Date", "End Date", "Stock Name", "Invest Value"});

                // Write data
                writer.writeNext(new String[]{
                        stock.getStartDate().toString(),
                        stock.getEndDate().toString(),
                        stock.getStockName(),
                        String.valueOf(stock.getInvestValue())
                });
            }

            // Return the download URL

            return "/csvexport/" + fileName;

        } catch(Exception e) {
            // Handle exceptions
            e.printStackTrace();
            throw new Exception("Failed to generate a CSV file: " + e.getMessage());
        } finally {System.out.println("CSV File generated.");}
    }
}
