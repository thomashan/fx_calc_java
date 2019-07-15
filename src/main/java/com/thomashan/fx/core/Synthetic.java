package com.thomashan.fx.core;

import java.util.Optional;

public interface Synthetic<C, D extends BaseTerms> extends Identity {
    /**
     * Cross with another element to produce a synthesised value. It should keep a state of all underlying crosses.
     * E.g. AUDUSD add USDJPY should keep underlying AUDUSD and USDJPY currency pairs.
     *
     * @param d Cross with another element
     * @return T synthesised value
     */
    C add(D d);

    /**
     * Is able to produce a crossable value. Should be called after {@link #add(BaseTerms)}.
     * Some results are not crossable (e.g. AUDUSD add EURGBP).
     *
     * @return
     */
    boolean isCrossable();

    /**
     * Is crossable and is not an identity.
     *
     * @return
     */
    default boolean isValid() {
        return isCrossable() && !isIdentity();
    }

    /**
     * The derived value of the synthesised value.
     * E.g. AUDUSD add USDJPY should produce a derived value of AUDJPY.
     *
     * @return
     */
    Optional<D> getSynthesisedValue();
}
