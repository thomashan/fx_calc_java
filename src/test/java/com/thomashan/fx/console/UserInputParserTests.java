package com.thomashan.fx.console;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserInputParserTests {
    @Test
    public void testValidInput_ShouldReturnTrue_IfNormalInput() {
        assertTrue(UserInputParser.validInput("AUD 100.00 in USD"));
    }

    @Test
    public void testValidInput_ShouldReturnTrue_IfAMountHasNoDecimal() {
        assertTrue(UserInputParser.validInput("AUD 100 in USD"));
    }

    @Test
    public void testValidInput_ShouldReturnFalse_IfCurrency1IsGreaterThan3Chars() {
        assertFalse(UserInputParser.validInput("AUDD 100 in USD"));
    }

    @Test
    public void testValidInput_ShouldReturnFalse_IfCurrency1IsLessThan3Chars() {
        assertFalse(UserInputParser.validInput("AU 100 in USD"));
    }

    @Test
    public void testValidInput_ShouldReturnFalse_IfCurrency2IsGreaterThan3Chars() {
        assertFalse(UserInputParser.validInput("AUD 100 in USDD"));
    }

    @Test
    public void testValidInput_ShouldReturnFalse_IfCurrency2IsLessThan3Chars() {
        assertFalse(UserInputParser.validInput("AUD 100 in US"));
    }

    @Test
    public void testGetUserInputs_ReturnsUserInput_IfValidInput() {
        assertEquals(Optional.of(UserInput.of("AUD", "USD", "100.00")), UserInputParser.getUserInputs("AUD 100.00 in USD"));
    }

    @Test
    public void testGetUserInputs_ReturnsEmpty_IfNotAValidInput() {
        assertEquals(Optional.empty(), UserInputParser.getUserInputs("AUD 100.00 in USDD"));
    }
}
