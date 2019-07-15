package com.thomashan.fx.core;

import java.util.Currency;

public final class Quote implements BaseTerms<Quote> {
    private final CurrencyPair instrument;
    private final Price rate;

    private Quote(CurrencyPair instrument, Price rate) {
        this.instrument = instrument;
        this.rate = rate;
    }

    public static Quote of(CurrencyPair instrument, Price rate) {
        return new Quote(instrument, rate);
    }

    @Override
    public Currency getBase() {
        return instrument.getBase();
    }

    @Override
    public Currency getTerms() {
        return instrument.getTerms();
    }

    @Override
    public Quote invert() {
        return of(instrument.invert(), rate.reciprocal());
    }

    public CurrencyPair getInstrument() {
        return instrument;
    }

    public Price getRate() {
        return rate;
    }

    @Override
    public int hashCode() {
        return instrument.hashCode() + 3 * rate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Quote)) {
            return false;
        }

        Quote other = (Quote) obj;

        return instrument.equals(other.getInstrument()) && rate.equals(other.getRate());
    }

    @Override
    public String toString() {
        return instrument.toString() + "@" + rate.toString();
    }
}
