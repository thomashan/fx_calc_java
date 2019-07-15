package com.thomashan.fx.console;

import java.text.DecimalFormat;
import java.util.Currency;

public final class CurrencyDisplay {
    private static final int defaultDps = 2;
    private static final DecimalFormat defaultDecimalFormat = createDecimalFormat(defaultDps);
    private final Currency currency;
    private final int dps;
    private final DecimalFormat decimalFormat;

    private CurrencyDisplay(Currency currency, int dps, DecimalFormat decimalFormat) {
        this.currency = currency;
        this.dps = dps;
        this.decimalFormat = decimalFormat;
    }

    public static CurrencyDisplay of(Currency currency) {
        return new CurrencyDisplay(currency, defaultDps, defaultDecimalFormat);
    }

    public static CurrencyDisplay of(Currency currency, int dps) {
        return new CurrencyDisplay(currency, dps, createDecimalFormat(dps));
    }

    private static DecimalFormat createDecimalFormat(int dps) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMinimumFractionDigits(dps);
        decimalFormat.setMaximumFractionDigits(dps);
        decimalFormat.setGroupingUsed(false);

        return decimalFormat;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getDps() {
        return dps;
    }

    public DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }
}
