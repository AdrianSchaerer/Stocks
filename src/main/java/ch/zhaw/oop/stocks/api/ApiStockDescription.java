package ch.zhaw.oop.stocks.api;

/**
 * <p> * This class represents the model for stocks.</p>
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
public class ApiStockDescription {

    private String symbol;      //"AACG"
    private String name;        //"ATA Creativity Global"
    private String currency;    //"USD"
    private String exchange;    //"NASDAQ"
    private String mic_code;    //"XNMS"
    private String country;     //"United States"
    private String type;        //"Depositary Receipt"

    /**
     * Gets symbol.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets symbol.
     *
     * @param symbol the symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets currency.
     *
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets currency.
     *
     * @param currency the currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Gets exchange.
     *
     * @return the exchange
     */
    public String getExchange() {
        return exchange;
    }

    /**
     * Sets exchange.
     *
     * @param exchange the exchange
     */
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    /**
     * Gets mic code.
     *
     * @return the mic code
     */
    public String getMic_code() {
        return mic_code;
    }

    /**
     * Sets mic code.
     *
     * @param mic_code the mic code
     */
    public void setMic_code(String mic_code) {
        this.mic_code = mic_code;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }
}
