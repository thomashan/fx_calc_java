package com.thomashan.fx.core;

import com.thomashan.fx.OverridesEqualsAndHashcode;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriceTests implements OverridesEqualsAndHashcode {
    @Test
    public void testDiv_ShouldReturnValue_IfTheResultIsRepeatingDecimal() {
        assertEquals(Price.of(new BigDecimal("0.11111111111111111111")), Price.of(1).div(Price.of(9)));
    }

    @Test
    public void testMultiply_ShouldReturnCorrectValue() {
        assertEquals(Price.of(9), Price.of(3).multiply(Price.of(3)));
    }

    @Test
    public void testMinus_ShouldReturnCorrectValue() {
        assertEquals(Price.of(3), Price.of(6).minus(Price.of(3)));
    }

    @Test
    public void testPlus_ShouldReturnCorrectValue() {
        assertEquals(Price.of(3), Price.of(1).plus(Price.of(2)));
    }

    @Test
    public void testReciprocal_ShouldReturnCorrectValue() {
        assertEquals(Price.of(0.1), Price.of(10).reciprocal());
    }

    @Test
    public void testOf_StripsOffTrailingZeroes_ForDouble() {
        assertEquals(Price.of(1), Price.of(1d));
    }

    @Test
    public void testOf_StripsOffTrailingZeroes_ForBigDecimal() {
        assertEquals(Price.of(1), Price.of(new BigDecimal("1.000000")));
    }

    @Test
    public void testCompareTo_ReturnsZero_IfValuesAreEquals() {
        assertEquals(0, Price.of(1).compareTo(Price.of(1)));
    }

    @Test
    public void testCompareTo_ReturnsNegativeValue_IfOtherIsGreater() {
        assertTrue(Price.of(1).compareTo(Price.of(2)) < 0);
    }

    @Test
    public void testCompareTo_ReturnsPositiveValue_IfOtherIsLess() {
        assertTrue(Price.of(1).compareTo(Price.of(0)) > 0);
    }

    @Test
    @Override
    public void testEquals_ShouldReturnFalse_IfInstanceOfOtherClass() {
        assertFalse(Price.of(1).equals(new Object()));
    }

    @Test
    @Override
    public void testEquals_ShouldReturnTrue_IfUnderlyingValuesAreTheSame() {
        assertTrue(Price.of(1).equals(Price.of(1)));
    }

    @Test
    @Override
    public void testEquals_ShouldReturnFalse_IfUnderlyingValuesAreDifferent() {
        assertFalse(Price.of(1).equals(Price.of(1.1)));
    }

    @Test
    @Override
    public void testHashcode_ShouldReturnSameValue_IfUnderlyingValuesAreTheSame() {
        assertEquals(Price.of(1).hashCode(), Price.of(1).hashCode());
    }

    @Test
    @Override
    public void testHashcode_ShouldReturnDifferentValue_IfUnderlyingValuesAreDifferent() {
        assertNotEquals(Price.of(1).hashCode(), Price.of(1.1).hashCode());
    }
}
