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
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (isExist(r.getUuid())) {
            System.out.println("This UUID already exists");
            return;
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
        if (!isExist(uuid)) {
            System.out.println("This UUID doesn't exist");
        } else {
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
    }

    public void update(Resume resume) {
        //реализовать, сначала проверка есть ли такое резюме
    }


    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public boolean isExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return true;
            }
        }
        return false;

    }
}

