package com.thomashan.fx.console;

import com.thomashan.fx.core.Currencies;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

public final class CurrencyDisplays {
    public static final CurrencyDisplay AUD = CurrencyDisplay.of(Currencies.AUD);
    public static final CurrencyDisplay CAD = CurrencyDisplay.of(Currencies.CAD);
    public static final CurrencyDisplay CNY = CurrencyDisplay.of(Currencies.CNY);
    public static final CurrencyDisplay CZK = CurrencyDisplay.of(Currencies.CZK);
    public static final CurrencyDisplay DKK = CurrencyDisplay.of(Currencies.DKK);
    public static final CurrencyDisplay EUR = CurrencyDisplay.of(Currencies.EUR);
    public static final CurrencyDisplay GBP = CurrencyDisplay.of(Currencies.GBP);
    public static final CurrencyDisplay JPY = CurrencyDisplay.of(Currencies.JPY, 0);
    public static final CurrencyDisplay NOK = CurrencyDisplay.of(Currencies.NOK);
    public static final CurrencyDisplay NZD = CurrencyDisplay.of(Currencies.NZD);
    public static final CurrencyDisplay USD = CurrencyDisplay.of(Currencies.USD);
    public static final Map<Currency, CurrencyDisplay> values = unmodifiableMap(initialiseValues());

    private CurrencyDisplays() {
        throw new AssertionError();
    }

    private static Map<Currency, CurrencyDisplay> initialiseValues() {
        Map<Currency, CurrencyDisplay> values = new HashMap<>();
        values.put(Currencies.AUD, AUD);
        values.put(Currencies.CAD, CAD);
        values.put(Currencies.CNY, CNY);
        values.put(Currencies.CZK, CZK);
        values.put(Currencies.DKK, DKK);
        values.put(Currencies.EUR, EUR);
        values.put(Currencies.GBP, GBP);
        values.put(Currencies.JPY, JPY);
        values.put(Currencies.NOK, NOK);
        values.put(Currencies.NZD, NZD);
        values.put(Currencies.USD, USD);

        return values;
    }
}
