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


/**
 * The type Stock service test.
 */
class StockServiceTest {

    /**
     * The Stock pojo.
     */
// ADR: Request from Web
    StockPOJO stockPOJO = new StockPOJO();
    /**
     * The Api stock value list pojo.
     */
// ADR: ApiStockValueList
    ApiStockValueListPOJO apiStockValueListPOJO = new ApiStockValueListPOJO();
    /**
     * The Value 1.
     */
// ADR: ApiStockValues
    ApiStockValuePOJO value1 = new ApiStockValuePOJO();
    /**
     * The Value 2.
     */
    ApiStockValuePOJO value2 = new ApiStockValuePOJO();
    /**
     * The Value 3.
     */
    ApiStockValuePOJO value3 = new ApiStockValuePOJO();
    /**
     * The List.
     */
// ADR: Adding the values to ArrayList
    List<ApiStockValuePOJO> list = new ArrayList<>();

    /**
     * Sets up.
     */
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

    /**
     * Stock date check test start date.
     */
    @Test
    void stockDateCheckTestStartDate() {
        StockServicePOJO.stockDateCheck(apiStockValueListPOJO,stockPOJO);

        // ADR: Start Date: Input was 2021-01-01 -> valid date is 2021-1-4
        assertEquals(stockPOJO.getStartDate(), LocalDate.of(2021, 1, 4));
    }

    /**
     * Stock date check test end date.
     */
    @Test
    void stockDateCheckTestEndDate() {
        StockServicePOJO.stockDateCheck(apiStockValueListPOJO,stockPOJO);
        // ADR: End Date: Input was 2021-01-01 -> valid date is 2021-1-6
        assertEquals(stockPOJO.getEndDate(), LocalDate.of(2021, 1, 6));
    }

}