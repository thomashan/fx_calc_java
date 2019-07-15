package com.thomashan.fx.core;

import java.util.List;
import java.util.Optional;

import static com.thomashan.fx.core.CollectionsUtil.createAndAdd;

public final class SyntheticQuote extends SynthesisedValue<SyntheticQuote, Quote> {
    private SyntheticQuote(Quote quote) {
        super(quote);
    }

    private SyntheticQuote(List<Quote> quotes, Optional<Quote> lastSynthesisedValue) {
        super(quotes, lastSynthesisedValue);
    }

    public static SyntheticQuote of(Quote quote) {
        return new SyntheticQuote(quote);
    }

    @Override
    protected Quote createLeftBaseMatchingRightBase(Quote left, Quote right) {
        CurrencyPair currencyPair = CurrencyPair.of(left.getTerms(), right.getTerms());
        Price price = right.getRate().div(left.getRate());

        return Quote.of(currencyPair, price);
    }

    @Override
    protected Quote createLeftTermsMatchingRightBase(Quote left, Quote right) {
        CurrencyPair currencyPair = CurrencyPair.of(left.getBase(), right.getTerms());
        Price price = left.getRate().multiply(right.getRate());

        return Quote.of(currencyPair, price);
    }

    @Override
    protected Quote createLeftBaseMatchingRightTerms(Quote left, Quote right) {
        CurrencyPair currencyPair = CurrencyPair.of(left.getTerms(), right.getBase());
        Price price = left.getRate().multiply(right.getRate()).reciprocal();

        return Quote.of(currencyPair, price);
    }

    @Override
    protected Quote createLeftTermsMatchingRightTerms(Quote left, Quote right) {
        CurrencyPair currencyPair = CurrencyPair.of(left.getBase(), right.getBase());
        Price price = left.getRate().div(right.getRate());

        return Quote.of(currencyPair, price);
    }


    @Override
    public SyntheticQuote add(Quote quote) {
        return new SyntheticQuote(createAndAdd(getElements(), quote), getSynthesisedValue());
    }
}
