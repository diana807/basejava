package com.urise.webapp.storage;

import com.urise.webapp.exception.*;

import com.urise.webapp.model.Resume;


public abstract class AbstractStorage implements Storage {

    protected abstract Resume doGet(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);


    @Override
    public Resume get(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(searchKey);
    }

    @Override
    public void update(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            doUpdate(r, searchKey);
        }
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isExist(searchKey)) {
            throw new ExistStorageException(r.getUuid());

        } else {
            doSave(r, searchKey);
        }
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(searchKey);
        }
    }
}