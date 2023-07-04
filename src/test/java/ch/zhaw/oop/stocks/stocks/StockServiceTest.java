package ch.zhaw.oop.stocks.stocks;


import ch.zhaw.oop.stocks.api.ApiStockValue;
import ch.zhaw.oop.stocks.api.ApiStockValueList;
import ch.zhaw.oop.stocks.stocks.pojo.StockServicePOJO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StockServiceTest {

    // ADR: Request from Web
    Stock stock = new Stock();
    // ADR: ApiStockValueList
    ApiStockValueList apiStockValueList = new ApiStockValueList();
    // ADR: ApiStockValues
    ApiStockValue value1 = new ApiStockValue();
    ApiStockValue value2 = new ApiStockValue();
    ApiStockValue value3 = new ApiStockValue();
    // ADR: Adding the values to ArrayList
    List<ApiStockValue> list = new ArrayList<>();

    @BeforeEach
    void setUp() {
        stock.setStockName("AAPL");
        stock.setInvestValue(1000);
        stock.setStartDate(LocalDate.of(2021, 1, 1));
        stock.setEndDate(LocalDate.of(2021, 1, 8));

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
        apiStockValueList.setValues(list);
    }
    @Test
    void stockDateCheckTestStartDate() {
        StockServicePOJO.stockDateCheck(apiStockValueList,stock);

        // ADR: Start Date: Input was 2021-01-01 -> valid date is 2021-1-4
        assertEquals(stock.getStartDate(), LocalDate.of(2021, 1, 4));
    }

    @Test
    void stockDateCheckTestEndDate() {
        StockServicePOJO.stockDateCheck(apiStockValueList,stock);
        // ADR: End Date: Input was 2021-01-01 -> valid date is 2021-1-6
        assertEquals(stock.getEndDate(), LocalDate.of(2021, 1, 6));
    }

}