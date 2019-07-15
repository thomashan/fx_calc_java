package com.thomashan.fx.core;

import java.util.Currency;

public interface BaseTerms<C> extends Identity {
    Currency getBase();

    Currency getTerms();

    C invert();

    default boolean contains(Currency that) {
        return getBase().equals(that) || getTerms().equals(that);
    }

    default boolean isIdentity() {
        return getBase().equals(getTerms());
    }
}
