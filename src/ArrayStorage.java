import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int countOfNotNullResumes = 0;

    void clear() {
        for (int i = 0; i < countOfNotNullResumes; i++) {
            storage[i] = null;
        }
        countOfNotNullResumes = 0;

    }

    void save(Resume r) {
        if (!Arrays.asList(getAll()).contains(r)) {
            storage[countOfNotNullResumes] = r;
            countOfNotNullResumes++;
        }
    }

    Resume get(String uuid) {
        Resume r = null;
        for (int i = 0; i < countOfNotNullResumes; i++) {
            if (storage[i].uuid.equals(uuid)) {
                r = storage[i];
                break;
            }
        }
        return r;
    }

    void delete(String uuid) {
        for (int i = 0; i < countOfNotNullResumes; i++) {
            if (storage[i].uuid.equals(uuid)) {
                while (i < countOfNotNullResumes) {
                    storage[i] = storage[i + 1];
                    i++;
                }
                countOfNotNullResumes--;
                break;
            }
        }
    }


    Resume[] getAll() {
        return Arrays.copyOf(storage, countOfNotNullResumes);
    }

    int size() {
        return countOfNotNullResumes;
    }
}

