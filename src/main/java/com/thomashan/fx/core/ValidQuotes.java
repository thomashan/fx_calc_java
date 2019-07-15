package com.thomashan.fx.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableMap;

public final class ValidQuotes {
    public static final Map<CurrencyPair, Quote> values = unmodifiableMap(createValidQuotes());

    private ValidQuotes() {
        throw new AssertionError();
    }

    private static Map<CurrencyPair, Quote> createValidQuotes() {
        Map<CurrencyPair, Quote> validQuotes = new HashMap<>();

        Stream<SyntheticQuote> syntheticQuotes = ReferenceRates.values
                .stream()
                .map(q -> SyntheticQuote.of(q));

        while (validQuotes.size() < ValidCurrencyPairs.values.size()) {
            List<SyntheticQuote> validSyntheticQuotes = syntheticQuotes
                    .filter(sq -> sq.isValid())
                    .filter(sq -> !validQuotes.containsKey(sq.getSynthesisedValue().get().getInstrument()))
                    .collect(Collectors.toList());

            validSyntheticQuotes
                    .stream()
                    .map(sq -> sq.getSynthesisedValue().get())
                    .forEach(q -> addQuoteAndInvertedQuoteToValidQuotes(validQuotes, q));

            syntheticQuotes = validSyntheticQuotes
                    .stream()
                    .flatMap(sq -> ReferenceRates.values.stream().map(q -> sq.add(q)));
        }

        return validQuotes;
    }

    private static void addQuoteToValidQuotes(Map<CurrencyPair, Quote> validQuotes, Quote quote) {
        CurrencyPair currencyPair = quote.getInstrument();
        if (!validQuotes.containsKey(currencyPair)) {
            validQuotes.put(currencyPair, quote);
        }
    }

    private static void addQuoteAndInvertedQuoteToValidQuotes(Map<CurrencyPair, Quote> validQuotes, Quote quote) {
        addQuoteToValidQuotes(validQuotes, quote);
        addQuoteToValidQuotes(validQuotes, quote.invert());
    }
}
