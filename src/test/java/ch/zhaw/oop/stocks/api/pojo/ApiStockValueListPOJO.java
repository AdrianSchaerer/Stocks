package ch.zhaw.oop.stocks.api.pojo;
import java.util.List;

/**
 * The type Api stock value list pojo.
 */
public class ApiStockValueListPOJO {

    //    private String meta;
    private List<ApiStockValuePOJO> values;

    /**
     * Gets values.
     *
     * @return the values
     */
    public List<ApiStockValuePOJO> getValues() {
        return values;
    }

    /**
     * Sets values.
     *
     * @param values the values
     */
//ADR: Added setter for testing purposes
    public void setValues(List<ApiStockValuePOJO> values) {
        this.values = values;
    }
}