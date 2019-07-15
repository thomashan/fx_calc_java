package com.thomashan.fx.console;

import com.thomashan.fx.core.CurrencyPair;

public class IdentityQuoteProcessor implements UserInputProcessor<CurrencyPairAmount, CurrencyPairAmount> {
    private final UserInputProcessor<CurrencyPairAmount, CurrencyPairAmount> next;

    private IdentityQuoteProcessor(UserInputProcessor<CurrencyPairAmount, CurrencyPairAmount> next) {
        this.next = next;
    }

    public static IdentityQuoteProcessor addNext(UserInputProcessor<CurrencyPairAmount, CurrencyPairAmount> userInputProcessor) {
        return new IdentityQuoteProcessor(userInputProcessor);
    }

    @Override
    public String processInput(CurrencyPairAmount currencyPairAmount) {
        CurrencyPair currencyPair = currencyPairAmount.getCurrencyPair();

        if (currencyPair.isIdentity()) {
            return MessageProvider.quote(currencyPair, currencyPairAmount.getAmount(), currencyPairAmount.getAmount());
        }

        return next.processInput(currencyPairAmount);
    }
}
