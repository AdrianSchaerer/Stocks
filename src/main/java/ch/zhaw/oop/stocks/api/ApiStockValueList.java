package ch.zhaw.oop.stocks.api;

import java.util.List;

/**
 * <h1>StockValueList</h1>
 * <p>This class contains a list of StockValue objects.</p>
 * <p>It is used in the class StockService for the method time_series</p>
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
public class ApiStockValueList {

//    private String meta;
    private List<ApiStockValue> values;

    public List<ApiStockValue> getValues() {
        return values;
    }
}
