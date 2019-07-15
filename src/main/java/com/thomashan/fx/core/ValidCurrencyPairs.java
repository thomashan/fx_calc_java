package com.thomashan.fx.core;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public final class ValidCurrencyPairs {
    public static final List<CurrencyPair> values = unmodifiableList(createValidCurrencyPairs());

    private ValidCurrencyPairs() {
        throw new AssertionError();
    }

    private static List<CurrencyPair> createValidCurrencyPairs() {
        return Currencies.values
                .stream()
                .flatMap(c1 -> Currencies.values.stream().map(c2 -> CurrencyPair.of(c1, c2)))
                .filter(cp -> !cp.isIdentity())
                .collect(Collectors.toList());
    }
}
