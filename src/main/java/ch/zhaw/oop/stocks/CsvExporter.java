package ch.zhaw.oop.stocks;

import org.springframework.stereotype.Component;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
/**
 * Exports the data from the Stocks object to a downloadable CSV file.
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@Component
public class CsvExporter {
    /* 	FEM: Stock data CSV Exporter
     *	Writes the Stock data to a CSV file
     * 	Usage: CSV File Export */
    public void exportToCsv(ch.zhaw.oop.stocks.Stock stock) {
        String filename = "stock_data.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(filename))) {
            List<String[]> data = Arrays.asList(
                    new String[]{"Start Date", "End Date", "Stock Name", "Start Value", "End Value", "Gain"},
                    new String[]{
                            stock.getStartDate(),
                            stock.getEndDate(),
                            stock.getStockName(),
                            String.valueOf(stock.getStartValue()),
                            String.valueOf(stock.getEndValue()),
                            String.valueOf(stock.getGain())
                    }
            );

            writer.writeAll(data);
            System.out.println("Stock data exported to CSV file: " + filename);
        } catch (IOException e) {
            System.out.println("Error exporting stock data to CSV file: " + e.getMessage());
        }
    }
}