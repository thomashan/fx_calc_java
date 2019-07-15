package com.thomashan.fx.console;

import com.thomashan.fx.core.CurrencyPair;
import com.thomashan.fx.core.Price;

import java.text.DecimalFormat;
import java.util.Currency;

public final class MessageProvider {
    private MessageProvider() {
        throw new AssertionError();
    }


    public static String invalidFormat() {
        return "Please provide a input in the form of:\n" +
                "<currency1> <amount> in <currency2>\n" +
                "Where currency is a three-character ISO 4217 format\n";
    }

    public static String notResolvable(String currency1, String currency2) {
        return String.format("Unable to find rate for %s/%s", currency1, currency2);
    }

    public static String quote(CurrencyPair currencyPair, Price baseAmount, Price termsAmount) {
        Currency base = currencyPair.getBase();
        Currency terms = currencyPair.getTerms();
        DecimalFormat baseFormat = CurrencyDisplays.values.get(base).getDecimalFormat();
        DecimalFormat termsFormat = CurrencyDisplays.values.get(terms).getDecimalFormat();

        return String.format("%s %s = %s %s", base, baseFormat.format(baseAmount.getValue()), terms, termsFormat.format(termsAmount.getValue()));
    }
}
