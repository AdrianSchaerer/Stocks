package ch.zhaw.oop.stocks.api;

/**
 * <h1>StockDescription</h1>
 * <p>This class represents the model for stocks.</p>
 * @author      Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version     1.0
 * @since       2023-06-23
 */
public class ApiStockDescription {

    private String symbol;      //"AACG"
    private String name;        //"ATA Creativity Global"
    private String currency;    //"USD"
    private String exchange;    //"NASDAQ"
    private String mic_code;    //"XNMS"
    private String country;     //"United States"
    private String type;        //"Depositary Receipt"

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getMic_code() {
        return mic_code;
    }

    public void setMic_code(String mic_code) {
        this.mic_code = mic_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}