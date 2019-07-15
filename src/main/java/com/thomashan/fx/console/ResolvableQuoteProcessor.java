package com.thomashan.fx.console;

import com.thomashan.fx.core.CurrencyPair;
import com.thomashan.fx.core.Price;
import com.thomashan.fx.core.Quote;
import com.thomashan.fx.core.ValidQuotes;

public class ResolvableQuoteProcessor implements UserInputProcessor<CurrencyPairAmount, CurrencyPairAmount> {
    private ResolvableQuoteProcessor() {
        // should be instantiated through terminate() method
    }

    public static ResolvableQuoteProcessor terminate() {
        return new ResolvableQuoteProcessor();
    }

    @Override
    public String processInput(CurrencyPairAmount currencyPairAmount) {
        CurrencyPair currencyPair = currencyPairAmount.getCurrencyPair();

        if (ValidQuotes.values.containsKey(currencyPair)) {
            Quote quote = ValidQuotes.values.get(currencyPair);
            Price baseAmount = currencyPairAmount.getAmount();
            Price termsAmount = quote.getRate().multiply(baseAmount);

            return MessageProvider.quote(currencyPair, baseAmount, termsAmount);
        }

        return MessageProvider.notResolvable(currencyPair.getBase().getCurrencyCode(), currencyPair.getTerms().getCurrencyCode());
    }
}
