package com.thomashan.fx.core;

import java.util.Currency;

public final class CurrencyPair implements BaseTerms<CurrencyPair> {
    private final Currency base;
    private final Currency terms;

    private CurrencyPair(Currency base, Currency terms) {
        this.base = base;
        this.terms = terms;
    }

    public static CurrencyPair of(Currency base, Currency terms) {
        return new CurrencyPair(base, terms);
    }

    @Override
    public Currency getBase() {
        return base;
    }

    @Override
    public Currency getTerms() {
        return terms;
    }

    @Override
    public CurrencyPair invert() {
        return of(terms, base);
    }

    @Override
    public int hashCode() {
        return base.hashCode() + 3 * terms.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CurrencyPair)) {
            return false;
        }

        CurrencyPair other = (CurrencyPair) obj;

        return base.equals(other.getBase()) && terms.equals(other.getTerms());
    }

    @Override
    public String toString() {
        return base.getCurrencyCode() + "/" + terms.getCurrencyCode();
    }
}
