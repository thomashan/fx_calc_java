package com.thomashan.fx.console;

import com.thomashan.fx.OverridesEqualsAndHashcode;
import com.thomashan.fx.core.Currencies;
import com.thomashan.fx.core.CurrencyPair;
import com.thomashan.fx.core.Price;
import org.junit.jupiter.api.Test;

import static com.thomashan.fx.core.Currencies.AUD;
import static com.thomashan.fx.core.Currencies.EUR;
import static com.thomashan.fx.core.Currencies.USD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CurrencyPairAmountTests implements OverridesEqualsAndHashcode {
    @Test
    @Override
    public void testEquals_ShouldReturnFalse_IfInstanceOfOtherClass() {
        assertFalse(CurrencyPairAmount.of(CurrencyPair.of(AUD, USD), Price.of(1)).equals(new Object()));
    }

    @Test
    @Override
    public void testEquals_ShouldReturnTrue_IfUnderlyingValuesAreTheSame() {
        assertTrue(CurrencyPairAmount.of(CurrencyPair.of(AUD, USD), Price.of(1)).equals(CurrencyPairAmount.of(CurrencyPair.of(AUD, USD), Price.of(1))));
    }

    @Test
    @Override
    public void testEquals_ShouldReturnFalse_IfUnderlyingValuesAreDifferent() {
        assertFalse(CurrencyPairAmount.of(CurrencyPair.of(AUD, USD), Price.of(1)).equals(CurrencyPairAmount.of(CurrencyPair.of(AUD, USD), Price.of(2))));
    }

    @Test
    public void testEquals_ShouldReturnFalse_IfUnderlyingValuesAreDifferent_BaseCurrencyDifferent() {
        assertFalse(CurrencyPairAmount.of(CurrencyPair.of(AUD, USD), Price.of(1)).equals(CurrencyPairAmount.of(CurrencyPair.of(EUR, USD), Price.of(1))));
    }

    @Test
    public void testEquals_ShouldReturnFalse_IfUnderlyingValuesAreDifferent_TermsCurrencyDifferent() {
        assertFalse(CurrencyPairAmount.of(CurrencyPair.of(AUD, USD), Price.of(1)).equals(CurrencyPairAmount.of(CurrencyPair.of(AUD, EUR), Price.of(1))));
    }

    @Test
    @Override
    public void testHashcode_ShouldReturnSameValue_IfUnderlyingValuesAreTheSame() {
        assertEquals(CurrencyPairAmount.of(CurrencyPair.of(AUD, USD), Price.of(1)).hashCode(),
                CurrencyPairAmount.of(CurrencyPair.of(AUD, USD), Price.of(1)).hashCode());
    }

    @Test
    @Override
    public void testHashcode_ShouldReturnDifferentValue_IfUnderlyingValuesAreDifferent() {
        assertNotEquals(CurrencyPairAmount.of(CurrencyPair.of(AUD, USD), Price.of(1)).hashCode(),
                CurrencyPairAmount.of(CurrencyPair.of(AUD, Currencies.CAD), Price.of(1)).hashCode());
    }
}
