package ch.zhaw.oop.stocks;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvBadConverterException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Exports the data from the Stocks object to a downloadable CSV file.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
public class CsvExporter {
    /* 	FEM: Stock data definition
     *	Defines the stock data values.
     *  Important: Column headers in the CSV match the field names.
     * 	Usage: Overall stock data handling and CSVexporter */
    private String startDate, endDate, stockName;
    private double startValue, endValue, gain;
    /**
     * FEM: Constructor for stock class objects.
     * @param startDate     Start Date of the stock period
     * @param endDate       End Date of the stock period
     * @param stockName     Unique Name Identifier of the stock, used for API Calls
     * @param startValue    Received Stock value at start date (close)
     * @param endValue      Received Stock value at end date (close)
     * @param gain          Calculated gain or loss during predefined time period
     */
    public CsvExporter(String startDate, String endDate, String stockName, float startValue, float endValue, float gain)
    {
        this.startDate=startDate;
        this.endDate=endDate;
        this.stockName=stockName;
        this.startValue=startValue;
        this.endValue=endValue;
        this.gain=gain;
    }
    /* 	FEM: Stock data string converter
     *	Converts the stock data into a string
     * 	Usage: String export for CSVexporter */
    public String toString() {
        return "Stock{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", stockName='" + stockName + '\'' +
                ", startValue='" + startValue + '\'' +
                ", endValue='" + endValue + '\'' +
                ", gain='" + gain + '\'' +
                '}';
    }
    /* 	FEM: Stock data string converter
     *	Converts the stock data into a string
     * 	Usage: String export for CSVexporter */

    public void exportToCsv() throws IOException {
        File file = new File("/src/main/resources/csvexport" + startDate + "_" + endDate + "_" + stockName + ".csv");            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);
            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            // adding header to csv
            String[] header = {"startDate","endDate","stockName","startValue","endValue","gain"};
            writer.writeNext(header);
            // add data to csv
            String stockDataString = startDate+", "+endDate+", "+stockName+", "+startValue+", "+endValue+", "+gain;
            String[] data1 = {stockDataString};
            writer.writeNext(data1);
            // closing writer connection
            writer.close();
        }
    /* 	FEM: Stock data string converter with stateful bean Usage
     *	Converts the stock data into a string
     * 	Usage: String export for CSVexporter */
    public void statefulBeanExport() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        // name of generated csv
        String CSV_FILE = "/src/main/resources/csvexport" + startDate + "_" + endDate + "_" + stockName + ".csv";
        try {
            FileWriter writer = new FileWriter(CSV_FILE);
            // create a list of employee
            List<Stock> StockList = new ArrayList<Stock>();
            // Create Mapping Strategy to arrange the column name in order
            ColumnPositionMappingStrategy mappingStrategy =
                    new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Stock.class);
            // Arrange column name as provided in below array
            String[] columns = new String[]
                    {"startDate", "endDate", "stockName", "startValue", "endValue", "gain"};
            mappingStrategy.setColumnMapping(columns);
            // Create StatefulBean Builder with Separator definition and no Quote Character
            // Creating StatefulBeanToCsv object with separator, no quotes and new mapping strategy
            StatefulBeanToCsvBuilder<Stock> builder =
                    new StatefulBeanToCsvBuilder(writer);
            StatefulBeanToCsv beanWriter =
                    builder.withMappingStrategy(mappingStrategy).withSeparator(',').withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
            // Write list to StatefulBeanToCsv object
            beanWriter.write(StockList);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvBadConverterException e) {
            throw new RuntimeException(e);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        } catch (CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        }
    }
}
