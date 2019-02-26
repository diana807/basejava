package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        for (Resume resume : getAll()) {
            if (resume.getUuid().equals(r.getUuid())) {
                System.out.println("That UUID already exists");
                return;
            }
        }
        if (r.getUuid() != null) {
            storage[size] = r;
            size++;
        }
    }


    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                while (i < size) {
                    storage[i] = storage[i + 1];
                    i++;
                }
                size--;
                break;
            }
        }
    }


    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}

