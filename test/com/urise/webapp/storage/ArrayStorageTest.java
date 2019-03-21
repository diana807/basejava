package com.urise.webapp.storage;

import static org.junit.Assert.*;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    Storage arrayStorage = new ArrayStorage();

    public ArrayStorageTest(Storage storage) {
        super(storage);
        this.arrayStorage = storage;
    }
}