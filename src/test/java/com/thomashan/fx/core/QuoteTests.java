package com.thomashan.fx.core;

import com.thomashan.fx.OverridesEqualsAndHashcode;
import org.junit.jupiter.api.Test;

import static com.thomashan.fx.core.Currencies.AUD;
import static com.thomashan.fx.core.Currencies.CAD;
import static com.thomashan.fx.core.Currencies.USD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuoteTests implements OverridesEqualsAndHashcode {
    private static final Quote AUDUSD = Quote.of(CurrencyPair.of(AUD, USD), Price.of(0.8371));

    @Test
    public void testToString() {
        assertEquals("AUD/USD@0.8371", AUDUSD.toString());
    }

    @Test
    public void testInvert_ShouldReturnInvertedBaseAndTerms() {
        assertEquals(Quote.of(CurrencyPair.of(USD, AUD), AUDUSD.getRate().reciprocal()), AUDUSD.invert());
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
        assertTrue(Quote.of(CurrencyPair.of(AUD, AUD), Price.of(1)).isIdentity());
    }

    @Test
    public void testIdentity_ShouldReturnFalse_IfBaseNotEqualsTerms() {
        assertFalse(AUDUSD.isIdentity());
    }

    @Override
    @Test
    public void testEquals_ShouldReturnFalse_IfInstanceOfOtherClass() {
        assertFalse(AUDUSD.equals(new Object()));
    }

    @Test
    @Override
    public void testEquals_ShouldReturnTrue_IfUnderlyingValuesAreTheSame() {
        assertTrue(AUDUSD.equals(Quote.of(AUDUSD.getInstrument(), AUDUSD.getRate())));
    }

    @Test
    @Override
    public void testEquals_ShouldReturnFalse_IfUnderlyingValuesAreDifferent() {
        assertFalse(AUDUSD.equals(Quote.of(AUDUSD.getInstrument(), Price.of(1))));
    }

    @Test
    public void testEquals_ShouldReturnFalse_IfUnderlyingValuesAreDifferent_CurrencyPairDifferent() {
        assertFalse(AUDUSD.equals(Quote.of(CurrencyPair.of(AUD, CAD), AUDUSD.getRate())));
    }

    @Test
    @Override
    public void testHashcode_ShouldReturnSameValue_IfUnderlyingValuesAreTheSame() {
        assertEquals(AUDUSD.hashCode(), AUDUSD.hashCode());
    }

    @Test
    @Override
    public void testHashcode_ShouldReturnDifferentValue_IfUnderlyingValuesAreDifferent() {
        assertNotEquals(Quote.of(CurrencyPair.of(AUD, USD), Price.of(1)).hashCode(), AUDUSD.hashCode());
    }
}
