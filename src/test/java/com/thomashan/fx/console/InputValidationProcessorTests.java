package com.thomashan.fx.console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputValidationProcessorTests {
    @Test
    public void testProcessInput_ShouldReturnInvalidFormatMessage_IfUserInputIsNotValid() {
        InputValidationProcessor inputValidationProcessor = InputValidationProcessor.addNext(null);

        assertEquals(MessageProvider.invalidFormat(), inputValidationProcessor.processInput("AUDD 100.00 in USD"));
    }

    @Test
    public void testProcessInput_ShouldCallProcessInputOfNext_IfNotIdentityQuote() {
        MockProcessor next = new MockProcessor();
        InputValidationProcessor inputValidationProcessor = InputValidationProcessor.addNext(next);
        inputValidationProcessor.processInput("AUD 100.00 in USD");

        assertTrue(next.isProcessInputCalled());
    }

    private static final class MockProcessor implements UserInputProcessor<UserInput, CurrencyPairAmount> {
        private boolean processInputCalled = false;

        @Override
        public String processInput(UserInput userInput) {
            processInputCalled = true;

            return null;
        }

        public boolean isProcessInputCalled() {
            return processInputCalled;
        }
    }
}
