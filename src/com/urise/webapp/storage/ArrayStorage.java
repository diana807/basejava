package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insert(Resume r) {
        storage[size] = r;
        size++;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
/**
 * AbstractArrayStorage*
 * >используйте паттерн Шаблонный метод
 * ~ для избавления от дублирования кода перенеси методы save, delete в AbstractArrayStorage из ArrayStorage и SortedArrayStorage, а часть кода что отличается реализуй на подобии getIndex
 *
 * SortedArrayStorage*
 *
 * ~ save - используй значение getIndex для вставки (если резюме не найдено посмотри что возвращает binarySearch) именно save определяет порядок в массиве


 * есть элемент - возвращает 0, 1, 2...
 * нет = -1, -2, ...
 */