package ch.zhaw.oop.stocks.stocks;

import ch.zhaw.oop.stocks.api.pojo.ApiStockValueListPOJO;
import ch.zhaw.oop.stocks.api.pojo.ApiStockValuePOJO;
import ch.zhaw.oop.stocks.stocks.pojo.StockPOJO;
import ch.zhaw.oop.stocks.stocks.pojo.StockServicePOJO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StockServiceTest {

    // ADR: Request from Web
    StockPOJO stockPOJO = new StockPOJO();
    // ADR: ApiStockValueList
    ApiStockValueListPOJO apiStockValueListPOJO = new ApiStockValueListPOJO();
    // ADR: ApiStockValues
    ApiStockValuePOJO value1 = new ApiStockValuePOJO();
    ApiStockValuePOJO value2 = new ApiStockValuePOJO();
    ApiStockValuePOJO value3 = new ApiStockValuePOJO();
    // ADR: Adding the values to ArrayList
    List<ApiStockValuePOJO> list = new ArrayList<>();

    @BeforeEach
    void setUp() {
        stockPOJO.setStockName("AAPL");
        stockPOJO.setInvestValue(1000);
        stockPOJO.setStartDate(LocalDate.of(2021, 1, 1));
        stockPOJO.setEndDate(LocalDate.of(2021, 1, 8));

        value1.setDatetime(LocalDate.of(2021,1,4));
        value1.setClose(129.41);

        value2.setDatetime(LocalDate.of(2021, 1, 5));
        value2.setClose(131.00999);

        value3.setDatetime(LocalDate.of(2021, 1, 6));
        value3.setClose(126.6);

        list.add(value1);
        list.add(value2);
        list.add(value3);

        // ADR: Setting the List to apiStockValueList
        apiStockValueListPOJO.setValues(list);
    }
    @Test
    void stockDateCheckTestStartDate() {
        StockServicePOJO.stockDateCheck(apiStockValueListPOJO,stockPOJO);

        // ADR: Start Date: Input was 2021-01-01 -> valid date is 2021-1-4
        assertEquals(stockPOJO.getStartDate(), LocalDate.of(2021, 1, 4));
    }

    @Test
    void stockDateCheckTestEndDate() {
        StockServicePOJO.stockDateCheck(apiStockValueListPOJO,stockPOJO);
        // ADR: End Date: Input was 2021-01-01 -> valid date is 2021-1-6
        assertEquals(stockPOJO.getEndDate(), LocalDate.of(2021, 1, 6));
    }

}