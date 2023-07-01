package ch.zhaw.oop.stocks.exporter;

import ch.zhaw.oop.stocks.stocks.Stock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private static final String CSV_EXPORT_DIRECTORY = "src/frontend/form/src/assets/csvexport/";

    @Value("src/frontend/form/src/assets/csvexport/")
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
        public String exportStockData(Stock stock, String filename) throws IOException {
        String filePath = CSV_EXPORT_DIRECTORY + filename;
        File file = new File(filePath);

        FileWriter writer = new FileWriter(file);
        CSVWriter csvWriter = new CSVWriter(writer);

        // Write the header
        csvWriter.writeNext(new String[] { "startDate", "endDate", "stockName", "startValue", "endValue", "investValue", "finalValue", "gainLossValue" });

        // Write the data row
        csvWriter.writeNext(new String[] {
                stock.getStartDate().toString(),
                stock.getEndDate().toString(),
                stock.getStockName(),
                String.valueOf(stock.getStartValue()),
                String.valueOf(stock.getEndValue()),
                String.valueOf(stock.getInvestValue()),
                String.valueOf(stock.getFinalValue()),
                String.valueOf(stock.getGainLossValue())
        });

        csvWriter.close();
        writer.close();

        String fileUrl = "http://localhost:4200/assets/csvexport/" + filename;
        return fileUrl;
    }
}
