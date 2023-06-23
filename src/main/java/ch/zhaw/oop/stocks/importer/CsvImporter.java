package ch.zhaw.oop.stocks.importer;
import ch.zhaw.oop.stocks.stocks.Stock;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

    public Stock importData(MultipartFile file) {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            // Read the CSV file and extract the required data
            String[] header = csvReader.readNext(); // Assuming the first row is the header
            String[] data = csvReader.readNext(); // Assuming there is only one row of data

            // Extract the data from the CSV row
            LocalDate startDate = LocalDate.parse(data[0]);
            LocalDate endDate = LocalDate.parse(data[1]);
            String stockName = data[2];
            double investValue = Double.parseDouble(data[3]);

            // Calculate additional fields
            // Assuming the makeAPICall method is implemented elsewhere
            double startValue = makeAPICall(startDate, stockName);
            double endValue = makeAPICall(endDate, stockName);
            double finalValue = (investValue / startValue) * endValue;
            double gainLossValue = finalValue - investValue;

            // Create and return the Stock object
            return new Stock();

        } catch (IOException | CsvValidationException e) {
            // Handle the exception (e.g., log the error, throw custom exception, etc.)
            e.printStackTrace();
        }

        // Return null or throw an exception if the import was unsuccessful
        return null;
    }

    // Placeholder method for making API calls
    private double makeAPICall(LocalDate date, String stockName) {
        // Implement your logic to make API calls and get the stock value for the given date and stock name
        // Replace this placeholder implementation with your actual code
        return 0.0;
    }
}
