package ch.zhaw.oop.stocks.api;

import java.util.List;

/**
 * <h1>StockDescriptionList</h1>
 * <p>This class contains a list of StockDescription objects.</p>
 * <p>It is used in StockService for the method time_series.</p>
 * @author      Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version     1.0
 * @since       2023-06-23
 */
public class ApiStockDescriptionList {

    private List<ApiStockDescription> data;

    public List<ApiStockDescription> getData() {
        return data;
    }


}
