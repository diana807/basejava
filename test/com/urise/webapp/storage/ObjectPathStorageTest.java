package com.urise.webapp.storage;


import com.urise.webapp.storage.serialazer.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest{

    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(),new ObjectStreamSerializer()));
    }
}