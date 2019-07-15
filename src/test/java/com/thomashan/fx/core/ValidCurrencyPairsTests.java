package com.thomashan.fx.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidCurrencyPairsTests {
    @Test
    public void testGetValues_ShouldNotReturnIdentityCurrencyPairs() {
        for (CurrencyPair currencyPair : ValidCurrencyPairs.values) {
            assertFalse(currencyPair.isIdentity(), currencyPair.toString());
        }
    }

    @Test
    public void testGetValues_ShouldReturnCorrectSize() {
        int expectedSize = Currencies.values.size() * Currencies.values.size() - Currencies.values.size();
        assertEquals(expectedSize, ValidCurrencyPairs.values.size());
    }
}
