package dhs.buystore;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Price {

    private Float value;
    private String currencyCode;

    public Price(Float value, String currencyCode) {
        this.value = value;
        this.currencyCode = currencyCode;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @JsonProperty("currency_code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
