package ch.zhaw.oop.stocks.api;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ApiStockServiceLiveTest {

    ApiStockService apiStockService = new ApiStockService();

    @Test
    void getListOfStocksExampleIBMLiveTest() {
        ApiStockDescriptionList apiStockDescriptionList = apiStockService.stocks();
        for (ApiStockDescription apiStockDescription : apiStockDescriptionList.getData()) {
            if(apiStockDescription.getSymbol().equals("IBM")) {
                assertEquals("International Business Machines Corporation", apiStockDescription.getName());
            }
        }
    }

    @Test
    void earliestTimestampIBMLiveTest() {
        ApiStockEarliestTimestamp apiStockEarliestTimestamp = apiStockService.earliestTimestamp("IBM");
        assertEquals(LocalDate.of(1970,1,2), apiStockEarliestTimestamp.getDatetime());
    }

    @Test
    void timeSeriesExampleIBMLiveTest() {
        ApiStockValueList apiStockValueList = apiStockService.timeSeries("IBM", LocalDate.of(2020,1,10), LocalDate.of(2020,1,15));
        for (ApiStockValue apiStockValue : apiStockValueList.getValues()) {
            if(apiStockValue.getDatetime().equals(LocalDate.of(2020,1,10))) {
                assertEquals(137.00000, apiStockValue.getOpen());
                assertEquals(137.87000, apiStockValue.getHigh());
                assertEquals(136.31000, apiStockValue.getLow());
                assertEquals(136.69000, apiStockValue.getClose());
                assertEquals(3255641, apiStockValue.getVolume());
            }
        }
    }
}