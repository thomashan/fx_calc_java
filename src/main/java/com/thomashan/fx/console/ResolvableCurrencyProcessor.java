package com.thomashan.fx.console;

import com.thomashan.fx.core.Currencies;
import com.thomashan.fx.core.CurrencyPair;
import com.thomashan.fx.core.Price;

import java.math.BigDecimal;
import java.util.Currency;

public class ResolvableCurrencyProcessor implements UserInputProcessor<UserInput, CurrencyPairAmount> {
    private final UserInputProcessor<CurrencyPairAmount, CurrencyPairAmount> next;

    private ResolvableCurrencyProcessor(UserInputProcessor<CurrencyPairAmount, CurrencyPairAmount> next) {
        this.next = next;
    }

    public static ResolvableCurrencyProcessor addNext(UserInputProcessor<CurrencyPairAmount, CurrencyPairAmount> userInputProcessor) {
        return new ResolvableCurrencyProcessor(userInputProcessor);
    }

    @Override
    public String processInput(UserInput userInput) {
        Currency currency1 = resolveCurrency(userInput.getCurrency1());
        Currency currency2 = resolveCurrency(userInput.getCurrency2());

        if (!bothCurrenciesResolvable(currency1, currency2)) {
            return MessageProvider.notResolvable(userInput.getCurrency1(), userInput.getCurrency2());
        }

        CurrencyPair currencyPair = CurrencyPair.of(currency1, currency2);
        Price amount = Price.of(new BigDecimal(userInput.getAmount()));

        return next.processInput(CurrencyPairAmount.of(currencyPair, amount));
    }

    private boolean resolvable(Currency currency) {
        if (currency != null && Currencies.values.contains(currency)) {
            return true;
        }

        return false;
    }

    private boolean bothCurrenciesResolvable(Currency currency1, Currency currency2) {
        if (resolvable(currency1) && resolvable(currency2)) {
            return true;
        }

        return false;
    }

    private Currency resolveCurrency(String currency) {
        try {
            return Currency.getInstance(currency);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
