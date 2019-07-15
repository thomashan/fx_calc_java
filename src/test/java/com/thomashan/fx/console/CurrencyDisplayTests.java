package com.thomashan.fx.console;

import com.thomashan.fx.core.Currencies;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyDisplayTests {
    private static final CurrencyDisplay AUD = CurrencyDisplay.of(Currencies.AUD);
    private static final CurrencyDisplay JPY = CurrencyDisplay.of(Currencies.JPY, 0);

    @Test
    public void testGetDecimalFormat_ShouldReturnCorrectValues() {
        assertEquals(2, AUD.getDecimalFormat().getMaximumFractionDigits());
        assertEquals(2, AUD.getDecimalFormat().getMinimumFractionDigits());
    }

    @Test
    public void testGetDecimalFormat_ShouldReturnCorrectValues_ForJPY() {
        assertEquals(0, JPY.getDecimalFormat().getMaximumFractionDigits());
        assertEquals(0, JPY.getDecimalFormat().getMinimumFractionDigits());
    }

    @Test
    public void testGetCurrency_ShouldReturnCorrectValues() {
        assertEquals(Currencies.AUD, AUD.getCurrency());
    }

    @Test
    public void testGetDps_ShouldReturnCorrectValues() {
        assertEquals(2, AUD.getDps());
    }
}
