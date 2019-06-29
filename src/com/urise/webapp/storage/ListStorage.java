package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return ((Integer)searchKey >= 0);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.set(((Integer) searchKey), r);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (Resume resume : storage) {
            if (uuid.equals(resume.getUuid())) {
                return storage.indexOf(resume);
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}

