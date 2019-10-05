package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage <Integer> {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

    public int size() {
        return size;
    }

    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[ searchKey];
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        storage[ searchKey] = r;
    }

    @Override
    protected void doDelete(Integer searchKey) {
        fillDeletedElement( searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r,  searchKey);
            size++;
        }
    }
}



