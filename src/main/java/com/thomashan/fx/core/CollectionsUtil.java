package com.thomashan.fx.core;

import java.util.ArrayList;
import java.util.List;

public final class CollectionsUtil {
    private CollectionsUtil() {
        throw new AssertionError();
    }

    /**
     * Create a new copy of the list.
     *
     * @return new List with values copied
     */
    public static <E> List<E> create(List<E> list) {
        return new ArrayList<>(list);
    }

    /**
     * Create a new copy of the list and add an element.
     *
     * @return new List with values copied and new element added
     */
    public static <E> List<E> createAndAdd(List<E> list, E elementToAdd) {
        List<E> newList = create(list);
        newList.add(elementToAdd);

        return newList;
    }
}
