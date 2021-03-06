package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage <Integer> {
    private List<Resume> list = new ArrayList<>();

    @Override
    protected void doDelete(Integer searchKey) {
        list.remove(( searchKey).intValue());
    }

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        list.add(r);
    }



    @Override
    protected Resume doGet(Integer searchKey) {
        return list.get( searchKey);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return ( searchKey >= 0);
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        list.set(( searchKey), r);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (uuid.equals(list.get(i).getUuid())) return i;
        }
        return -1;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }
}

