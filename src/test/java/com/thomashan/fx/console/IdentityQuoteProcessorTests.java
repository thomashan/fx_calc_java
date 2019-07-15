package com.thomashan.fx.console;

import com.thomashan.fx.core.CurrencyPair;
import com.thomashan.fx.core.Price;
import org.junit.jupiter.api.Test;

import static com.thomashan.fx.core.Currencies.AUD;
import static com.thomashan.fx.core.Currencies.USD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdentityQuoteProcessorTests {
    @Test
    public void testProcessInput_ShouldReturnQuote_IfIdentityQuote() {
        IdentityQuoteProcessor identityQuoteProcessor = IdentityQuoteProcessor.addNext(null);
        CurrencyPair currencyPair = CurrencyPair.of(AUD, AUD);
        Price baseAmount = Price.of(1);
        CurrencyPairAmount currencyPairAmount = CurrencyPairAmount.of(currencyPair, baseAmount);

        assertEquals(MessageProvider.quote(currencyPair, baseAmount, baseAmount), identityQuoteProcessor.processInput(currencyPairAmount));
    }

    @Test
    public void testProcessInput_ShouldCallProcessInputOfNext_IfNotIdentityQuote() {
        MockProcessor next = new MockProcessor();
        IdentityQuoteProcessor identityQuoteProcessor = IdentityQuoteProcessor.addNext(next);
        identityQuoteProcessor.processInput(CurrencyPairAmount.of(CurrencyPair.of(AUD, USD), Price.of(1)));

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
