package com.thomashan.fx.console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResolvableCurrencyProcessorTests {
    @Test
    public void testProcessInput_ShouldReturnNotResolvableMessage_IfCurrency1IsUnresolvable_InvalidISO4217Code() {
        ResolvableCurrencyProcessor resolvableCurrencyProcessor = ResolvableCurrencyProcessor.addNext(null);
        String currency1 = "AAA";
        String currency2 = "USD";
        UserInput userInput = UserInput.of(currency1, currency2, "100");

        assertEquals(MessageProvider.notResolvable(currency1, currency2), resolvableCurrencyProcessor.processInput(userInput));
    }

    @Test
    public void testProcessInput_ShouldReturnNotResolvableMessage_IfCurrency1IsUnresolvable_NotInCurrencies() {
        ResolvableCurrencyProcessor resolvableCurrencyProcessor = ResolvableCurrencyProcessor.addNext(null);
        String currency1 = "KRW";
        String currency2 = "USD";
        UserInput userInput = UserInput.of(currency1, currency2, "100");

        assertEquals(MessageProvider.notResolvable(currency1, currency2), resolvableCurrencyProcessor.processInput(userInput));
    }

    @Test
    public void testProcessInput_ShouldReturnNotResolvableMessage_IfCurrency2IsUnresolvable() {
        ResolvableCurrencyProcessor resolvableCurrencyProcessor = ResolvableCurrencyProcessor.addNext(null);
        String currency1 = "USD";
        String currency2 = "AAA";
        UserInput userInput = UserInput.of(currency1, currency2, "100");

        assertEquals(MessageProvider.notResolvable(currency1, currency2), resolvableCurrencyProcessor.processInput(userInput));
    }

    @Test
    public void testProcessInput_ShouldReturnNotResolvableMessage_IfCurrency2IsUnresolvable_NotInCurrencies() {
        ResolvableCurrencyProcessor resolvableCurrencyProcessor = ResolvableCurrencyProcessor.addNext(null);
        String currency1 = "USD";
        String currency2 = "KRW";
        UserInput userInput = UserInput.of(currency1, currency2, "100");

        assertEquals(MessageProvider.notResolvable(currency1, currency2), resolvableCurrencyProcessor.processInput(userInput));
    }

    @Test
    public void testProcessInput_ShouldCallProcessInputOfNext() {
        MockProcessor next = new MockProcessor();
        ResolvableCurrencyProcessor resolvableCurrencyProcessor = ResolvableCurrencyProcessor.addNext(next);
        resolvableCurrencyProcessor.processInput(UserInput.of("AUD", "USD", "100"));

        assertTrue(next.isProcessInputCalled());
    }

    private static final class MockProcessor implements UserInputProcessor<CurrencyPairAmount, CurrencyPairAmount> {
        private boolean processInputCalled = false;

        @Override
        public String processInput(CurrencyPairAmount userInput) {
            processInputCalled = true;

            return null;
        }

        public boolean isProcessInputCalled() {
            return processInputCalled;
        }
    }
}
