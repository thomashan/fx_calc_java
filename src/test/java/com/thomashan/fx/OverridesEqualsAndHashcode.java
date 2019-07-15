package com.thomashan.fx;

public interface OverridesEqualsAndHashcode {
    void testEquals_ShouldReturnFalse_IfInstanceOfOtherClass();

    void testEquals_ShouldReturnTrue_IfUnderlyingValuesAreTheSame();

    void testEquals_ShouldReturnFalse_IfUnderlyingValuesAreDifferent();

    void testHashcode_ShouldReturnSameValue_IfUnderlyingValuesAreTheSame();

    void testHashcode_ShouldReturnDifferentValue_IfUnderlyingValuesAreDifferent();
}
