package com.thomashan.fx.core;

import com.thomashan.fx.OverridesEqualsAndHashcode;
import org.junit.jupiter.api.Test;

import java.util.Currency;

import static com.thomashan.fx.core.Currencies.AUD;
import static com.thomashan.fx.core.Currencies.CAD;
import static com.thomashan.fx.core.Currencies.USD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CurrencyPairTests implements OverridesEqualsAndHashcode {
    private static final CurrencyPair AUDUSD = CurrencyPair.of(Currency.getInstance("AUD"), Currency.getInstance("USD"));
    private static final CurrencyPair CADUSD = CurrencyPair.of(Currency.getInstance("CAD"), Currency.getInstance("USD"));

    @Test
    public void testToString() {
        assertEquals("AUD/USD", AUDUSD.toString());
    }

    @Test
    public void testInvert_ShouldReturnInvertedBaseAndTerms() {
        assertEquals(CurrencyPair.of(USD, AUD), AUDUSD.invert());
    }

    @Test
    public void testContains_ShouldReturnTrue_IfThatEqualsBase() {
        assertTrue(AUDUSD.contains(AUD));
    }

    @Test
    public void testContains_ShouldReturnTrue_IfThatEqualsTerms() {
        assertTrue(AUDUSD.contains(USD));
    }

    @Test
    public void testContains_ShouldReturnFalse_IfThatIsNull() {
        assertFalse(AUDUSD.contains(null));
    }

    @Test
    public void testContains_ShouldReturnFalse_IfThatIsNotBaseOrTerms() {
        assertFalse(AUDUSD.contains(CAD));
    }

    @Test
    public void testIdentity_ShouldReturnTrue_IfBaseEqualsTerms() {
        assertTrue(CurrencyPair.of(AUD, AUD).isIdentity());
    }

    @Test
    public void testIdentity_ShouldReturnFalse_IfBaseNotEqualsTerms() {
        assertFalse(AUDUSD.isIdentity());
    }

    @Test
    @Override
    public void testEquals_ShouldReturnFalse_IfInstanceOfOtherClass() {
        assertFalse(AUDUSD.equals(new Object()));
    }

    @Test
    @Override
    public void testEquals_ShouldReturnTrue_IfUnderlyingValuesAreTheSame() {
        assertTrue(AUDUSD.equals(CurrencyPair.of(AUD, USD)));
    }

    @Test
    @Override
    public void testEquals_ShouldReturnFalse_IfUnderlyingValuesAreDifferent() {
        assertFalse(AUDUSD.equals(CADUSD));
    }

    @Test
    @Override
    public void testHashcode_ShouldReturnSameValue_IfUnderlyingValuesAreTheSame() {
        assertEquals(AUDUSD.hashCode(), AUDUSD.hashCode());
    }

    @Test
    @Override
    public void testHashcode_ShouldReturnDifferentValue_IfUnderlyingValuesAreDifferent() {
        assertNotEquals(AUDUSD.hashCode(), CADUSD.hashCode());
    }
}
