package com.thomashan.fx.core;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public final class Price implements Comparable<Price> {
    private static final int DEFAULT_SCALE = 20;
    private final BigDecimal value;

    private Price(BigDecimal value) {
        this.value = value.setScale(DEFAULT_SCALE, HALF_UP).stripTrailingZeros();
    }

    public static Price of(BigDecimal value) {
        return new Price(value);
    }

    public static Price of(long value) {
        return new Price(BigDecimal.valueOf(value));
    }

    public static Price of(double value) {
        return new Price(BigDecimal.valueOf(value));
    }

    public BigDecimal getValue() {
        return value;
    }

    public Price reciprocal() {
        return of(BigDecimal.ONE.setScale(DEFAULT_SCALE, HALF_UP).divide(value, HALF_UP));
    }

    public Price plus(Price other) {
        return Price.of(value.add(other.getValue()));
    }

    public Price minus(Price other) {
        return Price.of(value.subtract(other.getValue()));
    }

    public Price multiply(Price other) {
        return Price.of(value.multiply(other.getValue()));
    }

    public Price div(Price other) {
        return Price.of(value.setScale(DEFAULT_SCALE, HALF_UP).divide(other.getValue().setScale(DEFAULT_SCALE, HALF_UP), HALF_UP));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Price)) {
            return false;
        }

        Price other = (Price) obj;

        return value.equals(other.getValue());
    }

    @Override
    public int compareTo(Price o) {
        return value.compareTo(o.getValue());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
