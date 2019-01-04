/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        if (get(r.uuid)==null) {
            storage[size()] = r;
        }
    }

    Resume get(String uuid) {
        Resume r = null;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                r = storage[i];
            }
            break;
        }
        return r;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                while(i<size()-1){
                    storage[i]=storage[i++];
                }
                storage[i]=null;
            }
            break;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumesNotNull = new Resume[size()];
        for (int i = 0; i < size(); i++) {
            resumesNotNull[i] = storage[i];
        }
        return resumesNotNull;
    }

    int size() {
        int count = 0;
        for (Resume r : storage) {
            if (r != null) count++;
            else break;
        }
        return count;
    }
}

