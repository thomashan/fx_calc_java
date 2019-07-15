package com.thomashan.fx.core;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

abstract class SynthesisedValue<C, D extends BaseTerms> implements Synthetic<C, D> {
    private final List<D> elements;
    private final Optional<D> lastSynthesisedValue;

    protected SynthesisedValue(D element) {
        this.elements = unmodifiableList(asList(element));
        this.lastSynthesisedValue = Optional.empty();
    }

    protected SynthesisedValue(List<D> elements, Optional<D> lastSynthesisedValue) {
        this.elements = unmodifiableList(elements);
        this.lastSynthesisedValue = lastSynthesisedValue;
    }

    protected Optional<D> getLastSynthesisedValue() {
        return lastSynthesisedValue;
    }

    protected List<D> getElements() {
        return elements;
    }

    protected D getLastCurrencyPair() {
        return elements.get(elements.size() - 1);
    }

    @Override
    public Optional<D> getSynthesisedValue() {
        if (isCrossable()) {
            if (getLastSynthesisedValue().isPresent()) {
                D lastSynthesisedValue = getLastSynthesisedValue().get();

                if (leftContainsRightBase(lastSynthesisedValue, getLastCurrencyPair())) {
                    if (leftBaseMatchRightBase(lastSynthesisedValue, getLastCurrencyPair())) {
                        return Optional.of(createLeftBaseMatchingRightBase(lastSynthesisedValue, getLastCurrencyPair()));
                    } else {
                        return Optional.of(createLeftTermsMatchingRightBase(lastSynthesisedValue, getLastCurrencyPair()));
                    }
                } else {
                    if (lastSynthesisedValue.getBase().equals(getLastCurrencyPair().getTerms())) {
                        return Optional.of(createLeftBaseMatchingRightTerms(lastSynthesisedValue, getLastCurrencyPair()));
                    } else {
                        return Optional.of(createLeftTermsMatchingRightTerms(lastSynthesisedValue, getLastCurrencyPair()));
                    }
                }
            } else {
                return Optional.of(getLastCurrencyPair());
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean isCrossable() {
        if (isStart()) {
            return true;
        } else {
            return getLastSynthesisedValue().map((l) -> l.contains(getLastCurrencyPair().getBase()) || l.contains(getLastCurrencyPair().getTerms())).orElse(false);
        }
    }

    @Override
    public boolean isIdentity() {
        return getSynthesisedValue().map((v) -> v.isIdentity()).orElse(false);
    }

    protected abstract D createLeftBaseMatchingRightBase(D left, D right);

    protected abstract D createLeftTermsMatchingRightBase(D left, D right);

    protected abstract D createLeftBaseMatchingRightTerms(D left, D right);

    protected abstract D createLeftTermsMatchingRightTerms(D left, D right);


    private boolean leftContainsRightBase(D left, D right) {
        return left.contains(right.getBase());
    }

    private boolean leftBaseMatchRightBase(D left, D right) {
        return left.getBase().equals(right.getBase());
    }

    protected boolean isStart() {
        return elements.size() == 1;
    }
}
