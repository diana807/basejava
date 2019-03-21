package com.urise.webapp.storage;

import static org.junit.Assert.*;

public class SortedArrayStorageTest extends AbstractArrayStorageTest{

    public Storage sortedArrayStorage = new SortedArrayStorage();
    public SortedArrayStorageTest(Storage storage) {
        super(storage);
        this.sortedArrayStorage = storage;
    }
}