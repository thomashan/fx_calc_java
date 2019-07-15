package com.thomashan.fx.core;

import java.util.List;

import static com.thomashan.fx.core.Currencies.AUD;
import static com.thomashan.fx.core.Currencies.CAD;
import static com.thomashan.fx.core.Currencies.CNY;
import static com.thomashan.fx.core.Currencies.CZK;
import static com.thomashan.fx.core.Currencies.DKK;
import static com.thomashan.fx.core.Currencies.EUR;
import static com.thomashan.fx.core.Currencies.GBP;
import static com.thomashan.fx.core.Currencies.JPY;
import static com.thomashan.fx.core.Currencies.NOK;
import static com.thomashan.fx.core.Currencies.NZD;
import static com.thomashan.fx.core.Currencies.USD;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public final class ReferenceRates {
    public static final Quote AUDUSD = Quote.of(CurrencyPair.of(AUD, USD), Price.of(0.8371));
    public static final Quote CADUSD = Quote.of(CurrencyPair.of(CAD, USD), Price.of(0.8711));
    public static final Quote USDCNY = Quote.of(CurrencyPair.of(USD, CNY), Price.of(6.1715));
    public static final Quote EURUSD = Quote.of(CurrencyPair.of(EUR, USD), Price.of(1.2315));
    public static final Quote GBPUSD = Quote.of(CurrencyPair.of(GBP, USD), Price.of(1.5683));
    public static final Quote NZDUSD = Quote.of(CurrencyPair.of(NZD, USD), Price.of(0.7750));
    public static final Quote USDJPY = Quote.of(CurrencyPair.of(USD, JPY), Price.of(119.95));
    public static final Quote EURCZK = Quote.of(CurrencyPair.of(EUR, CZK), Price.of(27.6028));
    public static final Quote EURDKK = Quote.of(CurrencyPair.of(EUR, DKK), Price.of(7.4405));
    public static final Quote EURNOK = Quote.of(CurrencyPair.of(EUR, NOK), Price.of(8.6651));
    public static final List<Quote> values = unmodifiableList(asList(AUDUSD, CADUSD, USDCNY, EURUSD, GBPUSD, NZDUSD, USDJPY, EURCZK, EURDKK, EURNOK));

    private ReferenceRates() {
        throw new AssertionError();
    }
}
