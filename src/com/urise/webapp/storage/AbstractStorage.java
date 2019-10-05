package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;


public abstract class AbstractStorage <SK> implements Storage {

    protected abstract Resume doGet(SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doDelete(SK searchKey);

    protected abstract void doSave(Resume r, SK searchKey);

    public abstract List<Resume> doCopyAll();


    @Override
    public Resume get(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(searchKey);
    }

    @Override
    public void update(Resume r) {
        SK searchKey = getSearchKey(r.getUuid());
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            doUpdate(r, searchKey);
        }
    }

    @Override
    public void save(Resume r) {
        SK searchKey = getSearchKey(r.getUuid());
        if (isExist(searchKey)) {
            throw new ExistStorageException(r.getUuid());

        } else {
            doSave(r, searchKey);
        }
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(searchKey);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }


}