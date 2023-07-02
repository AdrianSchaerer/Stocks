package ch.zhaw.oop.stocks.api.pojo;
import java.util.List;

public class ApiStockValueListPOJO {

//    private String meta;
    private List<ApiStockValuePOJO> values;

    public List<ApiStockValuePOJO> getValues() {
        return values;
    }

    //ADR: Added setter for testing purposes
    public void setValues(List<ApiStockValuePOJO> values) {
        this.values = values;
    }
}
