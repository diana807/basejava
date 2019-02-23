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
        for (Resume resume : getAll()) {
            if (resume.uuid.equals(r.uuid)) {
                System.out.println("That UUID already exists");
                return;
            }
        }
        if (r.uuid != null) {
            storage[countOfNotNullResumes] = r;
            countOfNotNullResumes++;
        }
    }


    Resume get(String uuid) {
        for (int i = 0; i < countOfNotNullResumes; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
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

