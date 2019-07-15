package com.thomashan.fx.core;

import java.util.Currency;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public final class Currencies {
    public static final Currency AUD = Currency.getInstance("AUD");
    public static final Currency CAD = Currency.getInstance("CAD");
    public static final Currency CNY = Currency.getInstance("CNY");
    public static final Currency CZK = Currency.getInstance("CZK");
    public static final Currency DKK = Currency.getInstance("DKK");
    public static final Currency EUR = Currency.getInstance("EUR");
    public static final Currency GBP = Currency.getInstance("GBP");
    public static final Currency JPY = Currency.getInstance("JPY");
    public static final Currency NOK = Currency.getInstance("NOK");
    public static final Currency NZD = Currency.getInstance("NZD");
    public static final Currency USD = Currency.getInstance("USD");
    public static final List<Currency> values = unmodifiableList(asList(AUD, CAD, CNY, CZK, DKK, EUR, GBP, JPY, NOK, NZD, USD));

    private Currencies() {
        throw new AssertionError();
    }
}
