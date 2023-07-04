package ch.zhaw.oop.stocks.api;

import java.util.List;

/**
 * <p>This class contains a list of StockValue objects.</p>
 * <p>It is used in the class StockService for the method time_series</p>
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
public class ApiStockValueList {
    /**
     * Instantiates a new Api stock value list.
     */
    public ApiStockValueList() { }

    /**
     * Instantiates a new Api stock value list.
     *
     * @param values the values
     */
    public ApiStockValueList(List<ApiStockValue> values) {
        this.values = values;
    }
//    private String meta;
    private List<ApiStockValue> values;

    /**
     * Gets values.
     *
     * @return the values
     */
    public List<ApiStockValue> getValues() {
        return values;
    }

    /**
     * Sets values.
     *
     * @param values the values
     */
    public void setValues(List<ApiStockValue> values) {
        this.values = values;
    }
}
