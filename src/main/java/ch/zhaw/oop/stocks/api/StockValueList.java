package ch.zhaw.oop.stocks.api;

import java.util.List;

/**
 * <h1>StockValueList</h1>
 * <p>This class contains a list of StockValue objects.</p>
 * <p>It is used in the class StockService for the method time_series</p>
 * @author      Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version     1.0
 * @since       2023-06-23
 */
public class StockValueList {

//    private String meta;
    private List<StockValue> values;

    public List<StockValue> getValues() {
        return values;
    }
}
