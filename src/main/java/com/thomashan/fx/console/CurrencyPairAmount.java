package com.thomashan.fx.console;

import com.thomashan.fx.core.CurrencyPair;
import com.thomashan.fx.core.Price;

public final class CurrencyPairAmount {
    private final CurrencyPair currencyPair;
    private final Price amount;

    private CurrencyPairAmount(CurrencyPair currencyPair, Price amount) {
        this.currencyPair = currencyPair;
        this.amount = amount;
    }

    public static CurrencyPairAmount of(CurrencyPair currencyPair, Price amount) {
        return new CurrencyPairAmount(currencyPair, amount);
    }

    public CurrencyPair getCurrencyPair() {
        return currencyPair;
    }

    public Price getAmount() {
        return amount;
    }

    @Override
    public int hashCode() {
        return currencyPair.hashCode() + 3 * amount.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CurrencyPairAmount)) {
            return false;
        }

        CurrencyPairAmount other = (CurrencyPairAmount) obj;

        return currencyPair.equals(other.getCurrencyPair()) && amount.equals(other.getAmount());
    }
}
