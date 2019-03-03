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
            ;
        } else if (size == storage.length) {
            System.out.println("Storage overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("This UUID doesn't exist");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("This UUID doesn't exist");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }


    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("This UUID doesn't exist");
        } else {
            storage[index] = resume;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
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
