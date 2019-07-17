package com.fcb;

import java.util.List;

public interface DataStructure<T> {

    void put(T k);

    void delete(T k);

    boolean contains(T k);

    List<T> listItemsOderOfInsertion();
}
