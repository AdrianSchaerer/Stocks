package ch.zhaw.oop.stocks.api;

import java.util.List;

/**
 * <p>This class contains a list of StockDescription objects.</p>
 * <p>It is used in StockService for the method time_series.</p>
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
public class ApiStockDescriptionList {

    private List<ApiStockDescription> data;

    /**
     * Gets data.
     *
     * @return the data
     */
    public List<ApiStockDescription> getData() {
        return data;
    }


}
