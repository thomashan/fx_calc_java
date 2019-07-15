package com.thomashan.fx.console;

import com.thomashan.fx.core.Currencies;
import com.thomashan.fx.core.CurrencyPair;
import com.thomashan.fx.core.Price;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageProviderTests {
    @Test
    public void testInvalidFormat() {
        assertEquals("Please provide a input in the form of:\n" +
                "<currency1> <amount> in <currency2>\n" +
                "Where currency is a three-character ISO 4217 format\n", MessageProvider.invalidFormat());
    }

    @Test
    public void testNotResolvable() {
        assertEquals("Unable to find rate for AUD/USD", MessageProvider.notResolvable("AUD", "USD"));
    }

    @Test
    public void testQuote() {
        Price amount = Price.of(new BigDecimal("1.000000000000"));

        assertEquals("AUD 1.00 = USD 1.00", MessageProvider.quote(CurrencyPair.of(Currencies.AUD, Currencies.USD), amount, amount));
    }
}
