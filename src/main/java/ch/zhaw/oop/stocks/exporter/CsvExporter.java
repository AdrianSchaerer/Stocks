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
 * <p>FEM: This class is used to perform basic CSV exporter tasks.</p>
 * <p>Exports data from Stock object as CSV file to /src/frontend/form/src/assets/csvexport/</p>
 * <p>Also generates an unique file name based on search query.</p>
 * <p>Files are overwritten if the same values are used.</p>
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@Component
public class CsvExporter {

    private final Stock stock;
    // FEM: Final for file export folder. May be changed if necessary.
    private static final String CSV_EXPORT_DIRECTORY = "src/frontend/form/src/assets/csvexport/";

    /**
     * FEM: Constructor for the CsvExporter
     * Autoinjects stock when CsvExporter instance is created.
     * Provides proper initialization with required stock dependency.
     *
     * @param stock the stock Object
     */
    @Autowired
    public CsvExporter(Stock stock) {
        this.stock = stock;
    }

    /**
     * FEM: CSV Exporter method
     * Gets the stock data and writes it to a CSV file.
     *
     * @param stock    the stock
     * @param filename the filename
     * @return CSV File path (String)
     * @throws IOException the io exception
     */
    public String exportStockData(Stock stock, String filename) throws IOException {
        if (stock == null) {
            throw new IOException("Stock object is null");
        }

        String filePath = CSV_EXPORT_DIRECTORY + filename;
        File file = new File(filePath);

        try (FileWriter writer = new FileWriter(file); CSVWriter csvWriter = new CSVWriter(writer)) {
            // Write the header
            csvWriter.writeNext(new String[]{"startDate", "endDate", "stockName", "startValue", "endValue", "investValue", "finalValue", "gainLossValue"});

            // Write the data row
            csvWriter.writeNext(new String[]{
                    stock.getStartDate().toString(),
                    stock.getEndDate().toString(),
                    stock.getStockName(),
                    String.valueOf(stock.getStartValue()),
                    String.valueOf(stock.getEndValue()),
                    String.valueOf(stock.getInvestValue()),
                    String.valueOf(stock.getFinalValue()),
                    String.valueOf(stock.getGainLossValue())
            });

            // Generate download URL for frontend
            String fileUrl = "http://localhost:4200/assets/csvexport/" + filename;
            return fileUrl;
        } catch (IOException e) {
            // Re-throw the exception with the correct type
            throw new IOException("File not found", e);
        }
    }

    /**
     * Gets stock.
     *
     * @return the stock
     */
    public Stock getStock() {
        return stock;
    }
}
