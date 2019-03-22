package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[]{storage.get("uuid1"), storage.get("uuid2"), storage.get("uuid3")};
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_1), storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume resume = new Resume("uuid1");
        storage.update(resume);
        Assert.assertEquals(resume, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("uuid5"));
    }

    @Test
    public void save() {
        Resume newResume = new Resume("uuid4");
        storage.save(newResume);
        Assert.assertEquals(newResume, storage.get("uuid4"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume("uuid1"));
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() {
        try {
            for (int i = 0; i < 9997; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            Assert.fail("Error");
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }
}