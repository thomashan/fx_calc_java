package com.thomashan.fx.console;

import com.thomashan.fx.OverridesEqualsAndHashcode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserInputTests implements OverridesEqualsAndHashcode {
    @Test
    @Override
    public void testEquals_ShouldReturnFalse_IfInstanceOfOtherClass() {
        assertFalse(UserInput.of("AUD", "USD", "1").equals(new Object()));
    }

    @Test
    @Override
    public void testEquals_ShouldReturnTrue_IfUnderlyingValuesAreTheSame() {
        assertTrue(UserInput.of("AUD", "USD", "1").equals(UserInput.of("AUD", "USD", "1")));
    }

    @Test
    @Override
    public void testEquals_ShouldReturnFalse_IfUnderlyingValuesAreDifferent() {
        assertFalse(UserInput.of("AUD", "USD", "1").equals(UserInput.of("AUD", "USD", "2")));
    }

    @Test
    public void testEquals_ShouldReturnFalse_IfUnderlyingValuesAreDifferent_BaseCurrencyDifferent() {
        assertFalse(UserInput.of("AUD", "USD", "1").equals(UserInput.of("JPY", "USD", "1")));
    }

    @Test
    public void testEquals_ShouldReturnFalse_IfUnderlyingValuesAreDifferent_TermsCurrencyDifferent() {
        assertFalse(UserInput.of("AUD", "USD", "1").equals(UserInput.of("AUD", "EUR", "1")));
    }

    @Test
    @Override
    public void testHashcode_ShouldReturnSameValue_IfUnderlyingValuesAreTheSame() {
        assertEquals(UserInput.of("AUD", "USD", "1").hashCode(), UserInput.of("AUD", "USD", "1").hashCode());
    }

    @Test
    @Override
    public void testHashcode_ShouldReturnDifferentValue_IfUnderlyingValuesAreDifferent() {
        assertNotEquals(UserInput.of("AUD", "USD", "1").hashCode(), UserInput.of("AUD", "USD", "2").hashCode());
    }
}
