package com.thomashan.fx.core;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.thomashan.fx.core.Currencies.AUD;
import static com.thomashan.fx.core.Currencies.CAD;
import static com.thomashan.fx.core.Currencies.CZK;
import static com.thomashan.fx.core.Currencies.EUR;
import static com.thomashan.fx.core.Currencies.JPY;
import static com.thomashan.fx.core.Currencies.USD;
import static com.thomashan.fx.core.ReferenceRates.AUDUSD;
import static com.thomashan.fx.core.ReferenceRates.CADUSD;
import static com.thomashan.fx.core.ReferenceRates.EURCZK;
import static com.thomashan.fx.core.ReferenceRates.EURDKK;
import static com.thomashan.fx.core.ReferenceRates.EURUSD;
import static com.thomashan.fx.core.ReferenceRates.USDJPY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SyntheticQuoteTests {
    @Test
    public void testIsCrossable_ShouldReturnTrue_IfFirstCross() {
        assertTrue(SyntheticQuote.of(AUDUSD).isCrossable());
    }

    @Test
    public void testIsCrossable_ShouldReturnTrue_IffNewCurrencyPairBaseIsInTheLatestValue() {
        SyntheticQuote syntheticQuote = SyntheticQuote
                .of(AUDUSD)
                .add(Quote.of(CurrencyPair.of(AUD, CAD), Price.of(1)));

        assertTrue(syntheticQuote.isCrossable());
    }

    @Test
    public void testIsCrossable_ShouldReturnTrue_IffNewCurrencyPairTermsIsInTheLatestValue() {
        SyntheticQuote syntheticQuote = SyntheticQuote
                .of(AUDUSD)
                .add(CADUSD);

        assertTrue(syntheticQuote.isCrossable());
    }

    @Test
    public void testIsCrossable_ShouldFalse_IfNewCurrencyPairBaseOrTermsIsNotInTheLatestValue() {
        SyntheticQuote syntheticQuote = SyntheticQuote
                .of(AUDUSD)
                .add(EURCZK);

        assertFalse(syntheticQuote.isCrossable());
    }

    @Test
    public void testGetSynthesisedValue_ShouldReturnCorrectValue_IffNewCurrencyPairBaseIsCrossable() {
        SyntheticQuote syntheticQuote = SyntheticQuote
                .of(AUDUSD)
                .add(Quote.of(CurrencyPair.of(AUD, CAD), Price.of(1)));

        assertEquals(Optional.of(Quote.of(CurrencyPair.of(USD, CAD), Price.of(1).div(AUDUSD.getRate()))), syntheticQuote.getSynthesisedValue());
    }

    @Test
    public void testGetSynthesisedValue_ShouldReturnCorrectValue_IffNewCurrencyPairTermsIsCrossable() {
        SyntheticQuote syntheticQuote = SyntheticQuote
                .of(AUDUSD)
                .add(CADUSD);

        assertEquals(Optional.of(Quote.of(CurrencyPair.of(AUD, CAD), AUDUSD.getRate().div(CADUSD.getRate()))), syntheticQuote.getSynthesisedValue());
    }

    @Test
    public void testGetSynthesisedValue_ShouldReturnEmptyValue_IfNewCurrencyPairIsNotCrossable() {
        SyntheticQuote syntheticQuote = SyntheticQuote
                .of(AUDUSD)
                .add(EURCZK);

        assertEquals(Optional.empty(), syntheticQuote.getSynthesisedValue());
    }

    @Test
    public void testGetSynthesisedValue_ShouldReturnEmptyValue_IfLastSynthesisedValueEmpty() {
        SyntheticQuote syntheticQuote = SyntheticQuote
                .of(AUDUSD)
                .add(EURCZK)
                .add(EURDKK);

        assertEquals(Optional.empty(), syntheticQuote.getSynthesisedValue());
    }

    @Test
    public void testGetSynthesisedValue_ShouldGetFirstValue_IfFirstCross() {
        assertEquals(Optional.of(AUDUSD), SyntheticQuote.of(AUDUSD).getSynthesisedValue());
    }

    @Test
    public void testCreateLeftBaseMatchingRightBase_ShouldReturnCorrectValue() {
        Quote quote = SyntheticQuote
                .of(AUDUSD)
                .createLeftBaseMatchingRightBase(EURUSD, EURCZK);

        assertEquals(Quote.of(CurrencyPair.of(USD, CZK), EURCZK.getRate().div(EURUSD.getRate())), quote);
    }

    @Test
    public void testCreateLeftBaseMatchingRightTerms_ShouldReturnCorrectValue() {
        Quote quote = SyntheticQuote
                .of(AUDUSD)
                .createLeftBaseMatchingRightTerms(USDJPY, AUDUSD);

        assertEquals(Quote.of(CurrencyPair.of(JPY, AUD), USDJPY.getRate().multiply(AUDUSD.getRate()).reciprocal()), quote);
    }

    @Test
    public void testCreateLeftTermsMatchingRightBase_ShouldReturnCorrectValue() {
        Quote quote = SyntheticQuote
                .of(AUDUSD)
                .createLeftTermsMatchingRightBase(EURUSD, USDJPY);

        assertEquals(Quote.of(CurrencyPair.of(EUR, JPY), EURUSD.getRate().multiply(USDJPY.getRate())), quote);
    }

    @Test
    public void testCreateLeftTermsMatchingRightTerms_ShouldReturnCorrectValue() {
        Quote quote = SyntheticQuote
                .of(AUDUSD)
                .createLeftTermsMatchingRightTerms(AUDUSD, CADUSD);

        assertEquals(Quote.of(CurrencyPair.of(AUD, CAD), AUDUSD.getRate().div(CADUSD.getRate())), quote);
    }
}
