package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= storage.length) {
            System.out.println("Storage overflow");
        } else {
            int index = Math.abs(Arrays.binarySearch(getAll(), r) + 1);
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = r;
            size++;
        }
    }


    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index);
            storage[size - 1] = null;
            size--;
        }
    }


    @Override
    public Resume[] getAll() {
        return getSortedStorage();
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        int index = Arrays.binarySearch(getSortedStorage(), 0, size, searchKey);
        if (index >= 0) {
            return index;
        } else {
            return -1;
        }
    }

    private Resume[] getSortedStorage() {
        Resume[] sortedStorage = Arrays.copyOfRange(storage, 0, size);
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if ((sortedStorage[j].compareTo(sortedStorage[j + 1])) > 0) {
                    Resume tmp = sortedStorage[j];
                    sortedStorage[j] = sortedStorage[j + 1];
                    sortedStorage[j + 1] = tmp;
                }
            }
        }
        return sortedStorage;
    }

}


