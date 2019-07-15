package com.thomashan.fx.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidQuotesTests {
    @Test
    public void testGetValues_ShouldNotReturnIdentityCurrencyPairs() {
        for (Quote quote : ValidQuotes.values.values()) {
            assertFalse(quote.isIdentity(), quote.toString());
        }
    }

    @Test
    public void testGetValues_ShouldReturnCorrectSize() {
        int expectedSize = Currencies.values.size() * Currencies.values.size() - Currencies.values.size();
        assertEquals(expectedSize, ValidCurrencyPairs.values.size());
    }
}
