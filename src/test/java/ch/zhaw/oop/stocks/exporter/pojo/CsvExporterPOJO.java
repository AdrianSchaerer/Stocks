package ch.zhaw.oop.stocks.exporter.pojo;

import ch.zhaw.oop.stocks.stocks.pojo.StockPOJO;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FEM: This class is used to perform basic CSV exporter tasks.
 * Exports data from Stock object as CSV file to /resources/csvexport/
 * Also generates an unique file name based on search query.
 * Files are overwritten if the same values are used.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */

public class CsvExporterPOJO {

    private final StockPOJO stockPOJO;
    //ADR: Changed directory for testing
    private static final String CSV_EXPORT_DIRECTORY = "src/test/java/ch/zhaw/oop/stocks/exporter/csv/";
    private String csvExportDirectory;
    /** FEM: Constructor for the CsvExporter
     *  Autoinjects stock when CsvExporter instance is created.
     *  Provides proper initialization with required stock dependency.
     */

    public CsvExporterPOJO(StockPOJO stockPOJO) {
        this.stockPOJO = stockPOJO;
    }
    /** FEM: CSV Exporter method
     *  Gets the stock data and writes it to a CSV file.
     *  @return CSV File path (String)
     */

    public String exportStockData(StockPOJO stockPOJO, String filename) throws IOException {

        String filePath = CSV_EXPORT_DIRECTORY + filename;
        File file = new File(filePath);

        FileWriter writer = new FileWriter(file);
        CSVWriter csvWriter = new CSVWriter(writer);

        // Write the header
        csvWriter.writeNext(new String[] {
                "startDate",
                "endDate",
                "stockName",
                "startValue",
                "endValue",
                "investValue",
                "finalValue",
                "gainLossValue" });

        // Write the data row
        csvWriter.writeNext(new String[] {
                stockPOJO.getStartDate().toString(),
                stockPOJO.getEndDate().toString(),
                stockPOJO.getStockName(),
                String.valueOf(stockPOJO.getStartValue()),
                String.valueOf(stockPOJO.getEndValue()),
                String.valueOf(stockPOJO.getInvestValue()),
                String.valueOf(stockPOJO.getFinalValue()),
                String.valueOf(stockPOJO.getGainLossValue())
        });

        csvWriter.close();
        writer.close();

        //ADR: Changed directory for testing
        String fileUrl = "src/test/java/ch/zhaw/oop/stocks/exporter/csv/" + filename;
        return fileUrl;
    }
}
