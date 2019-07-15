package com.thomashan.fx.console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyDisplaysTests {
    @Test
    public void testSize() {
        assertEquals(11, CurrencyDisplays.values.size());
    }
}
