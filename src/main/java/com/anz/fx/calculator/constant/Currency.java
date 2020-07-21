package com.anz.fx.calculator.constant;

/**
 * Created by abhis on 20/07/2020.
 */
public enum Currency {
    AUD,
    CAD,
    CNY,
    CZK,
    DKK,
    EUR,
    GBP,
    JPY("0"),
    NOK,
    NZD,
    USD;

    private String precision = "0.00";

    Currency() {}

    Currency(String precision) {
        this.precision = precision;
    }

    public String getPrecision() {
        return precision;
    }
}
