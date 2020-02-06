package com.urise.webapp.storage;


import com.urise.webapp.storage.serialazer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest{

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(),new DataStreamSerializer()));
    }
}