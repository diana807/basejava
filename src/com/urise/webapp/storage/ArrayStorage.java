package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) >= 0) {
            System.out.println("This UUID already exists");
            return;
        } else if (getIndex(r.getUuid()) == (storage.length - 1)) {
            System.out.println("sorry, doesn't have enought place");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) >= 0) {
            return storage[getIndex(uuid)];
        } else {
            System.out.println("This UUID doesn't exist");
            return null;
        }
    }

    public void delete(String uuid) {
        if (getIndex(uuid) < 0) {
            System.out.println("This UUID doesn't exist");
        } else {
            int i = getIndex(uuid);
            while (i < size - 1) {
                storage[i] = storage[i + 1];
                storage[i + 1] = null;
                i++;
            }
            size--;
        }
    }

    public void update(Resume resume) {
        if (!isExist(resume.getUuid())) {
            System.out.println("This UUID doesn't exist");
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(resume.getUuid())) {
                    storage[i] = resume;
                    break;
                }
            }
        }
    }


    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private boolean isExist(String uuid) {
        if (getIndex(uuid) >= 0) {
            return true;
        } else return false;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
