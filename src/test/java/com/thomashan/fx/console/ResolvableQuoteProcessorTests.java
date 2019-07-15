package com.thomashan.fx.console;

import com.thomashan.fx.core.CurrencyPair;
import com.thomashan.fx.core.Price;
import com.thomashan.fx.core.ValidQuotes;
import org.junit.jupiter.api.Test;

import java.util.Currency;

import static com.thomashan.fx.core.Currencies.AUD;
import static com.thomashan.fx.core.Currencies.USD;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResolvableQuoteProcessorTests {
    @Test
    public void testProcessInput_ShouldReturnNotResolvableMessage_IfNotInValidQuotes() {
        ResolvableQuoteProcessor resolvableQuoteProcessor = ResolvableQuoteProcessor.terminate();
        String currency1 = "KRW";
        String currency2 = USD.getCurrencyCode();
        CurrencyPairAmount currencyPairAmount = CurrencyPairAmount.of(CurrencyPair.of(Currency.getInstance(currency1), USD), Price.of(1));

        assertEquals(MessageProvider.notResolvable(currency1, currency2), resolvableQuoteProcessor.processInput(currencyPairAmount));
    }

    @Test
    public void testProcessInput_ShouldReturnQuote() {
        ResolvableQuoteProcessor resolvableQuoteProcessor = ResolvableQuoteProcessor.terminate();
        CurrencyPair currencyPair = CurrencyPair.of(AUD, USD);
        Price baseAmount = Price.of(1);
        CurrencyPairAmount currencyPairAmount = CurrencyPairAmount.of(currencyPair, baseAmount);
        Price termsAmount = baseAmount.multiply(ValidQuotes.values.get(currencyPair).getRate());

        assertEquals(MessageProvider.quote(currencyPair, baseAmount, termsAmount), resolvableQuoteProcessor.processInput(currencyPairAmount));
    }
}
