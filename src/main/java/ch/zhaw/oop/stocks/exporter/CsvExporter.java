package ch.zhaw.oop.stocks.exporter;

import ch.zhaw.oop.stocks.stocks.Stock;
import org.springframework.stereotype.Component;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FEM: This class is used to perform basic CSV exporter tasks.
 * Exports data from Stock object as CSV file to /src/frontend/form/src/assets/csvexport/
 * Also generates a unique file name based on search query.
 * Files are overwritten if the same values are used.
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
            return "http://localhost:4200/assets/csvexport/" + filename;
        } catch (IOException e) {
            // Re-throw the exception with the correct type
            throw new IOException("File not found", e);
        }
    }

    /**
     * FEM: Method to return stock.
     *
     * @return the stock
     */
    public Stock getStock() {
        return stock;
    }


/*      @Deprecated
        // FEM: Old CSV Exporter Code:
        public String exportStockData(Stock stock) throws IOException {
        if (stock == null) {
            throw new IOException("Stock object is null");
        }

        String filename = stock.getStartDate() + "_" + stock.getEndDate() + "_" + stock.getStockName() + ".csv";
        String filePath = "src/main/resources/csvexport/" + filename;
        File file = new File(filePath);

        try (FileWriter writer = new FileWriter(file)) {
            // Write the header
            writer.write("startDate,endDate,stockName,startValue,endValue,investValue,finalValue,gainLossValue\n");

            // Write the data row
            writer.write(stock.getStartDate().toString() + "," +
                    stock.getEndDate().toString() + "," +
                    stock.getStockName() + "," +
                    stock.getStartValue() + "," +
                    stock.getEndValue() + "," +
                    stock.getInvestValue() + "," +
                    stock.getFinalValue() + "," +
                    stock.getGainLossValue() + "\n");

            // Generate download URL for frontend
            String fileUrl = "http://localhost:8080/csvexport/" + filename;
            return fileUrl;
        } catch (IOException e) {
            // Re-throw the exception with the correct type
            throw new IOException("File not found", e);
        }
    }*/


}
