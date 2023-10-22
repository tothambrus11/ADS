package sets;

import java.lang.reflect.Array;
import java.util.*;

public class HashSet<V> implements Set<V> {
    private int size = 0;
    private final ArrayList<ArrayList<V>> buckets = new ArrayList<>(MIN_CAPACITY);

    public static final int MIN_CAPACITY = 8;
    private static final Random RANDOM = new Random();
    private final int BIG_PRIME = 268435649;
    private final int compressionConstantA = 1 + RANDOM.nextInt(BIG_PRIME - 1); // [1, p-1]
    private final int compressionConstantB = RANDOM.nextInt(BIG_PRIME); // [0, p-1]

    public HashSet() {
        for (int i = 0; i < MIN_CAPACITY; i++) {
            this.buckets.add(new ArrayList<>());
        }
    }

    @Override
    public boolean add(V value) {
        int bucketId = hashAndCompress(value);
        var bucket = buckets.get(bucketId);
        if (bucket.contains(value)) return false;
        bucket.add(value);
        size++;
        if(loadFactor() >= 0.75) resize(2 * bucketCount());
        return true;
    }

    @Override
    public boolean remove(Object value) {
        int bucketId = hashAndCompress(value);
        var bucket = buckets.get(bucketId);
        if (bucket.remove(value)) {
            size--;
            if(loadFactor() <= 0.25) resize(bucketCount() / 2);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {
        boolean modified = false;
        for (var e : c) {
            modified |= add(e);
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (var e : c) {
            modified |= remove(e);
        }
        return modified;
    }

    @Override
    public boolean contains(Object value) {
        return stream().anyMatch(e -> Objects.equals(e, value));
    }

    class EntryIterator implements Iterator<V> {
        int bucketId = 0;
        int entryId = 0;

        @Override
        public boolean hasNext() {
            if (bucketId >= buckets.size()) return false;
            int entryId_ = entryId;
            int bucketId_ = bucketId;

            while (bucketId_ < buckets.size()) {
                entryId_++;
                if (entryId_ < buckets.get(bucketId_).size()) {
                    return true;
                } else {
                    bucketId_++;
                    entryId_ = -1;
                }
            }
            return false;
        }

        @Override
        public V next() {
            if (bucketId >= buckets.size()) throw new NoSuchElementException();
            while (bucketId < buckets.size()) {
                entryId++;
                if (entryId < buckets.get(bucketId).size()) {
                    return buckets.get(bucketId).get(entryId);
                } else {
                    bucketId++;
                    entryId = -1;
                }
            }
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new EntryIterator();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        buckets.clear();
        size = 0;
        for (int i = 0; i < MIN_CAPACITY; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    @Override
    public boolean retainAll(Collection<?> elementsToRetain) {
        boolean modified = false;
        for (var e : this) {
            if (!elementsToRetain.contains(e)) {
                remove(e);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        for (var bucket : buckets) {
            for (var el : bucket) {
                arr[i++] = el;
            }
        }
        return arr;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        T[] arr = (T[]) Array.newInstance(a.getClass(), size);
        int i = 0;
        for (var bucket : buckets) {
            for (var el : bucket) {
                arr[i++] = (T) el;
            }
        }
        return arr;
    }

    public int bucketCount() {
        return buckets.size();
    }

    public float loadFactor() {
        return (float) size / bucketCount();
    }

    private int hashAndCompress(Object value) {
        return Math.abs((Objects.hash(value) * compressionConstantA + compressionConstantB) % BIG_PRIME) % bucketCount();
    }

    private void resize(int N) {
        if (N < MIN_CAPACITY) return;
        List<V> buffer = stream().toList();

        buckets.clear();
        buckets.ensureCapacity(N);
        for (int i = 0; i < N; i++) {
            buckets.add(new ArrayList<>());
        }

        for (var e : buffer) {
            addWithoutCheck(e);
        }
    }

    /**
     * Adds an element to the hashset in guaranteed O(1) complexity without looping through the
     * bucket for existing entries. This method adds the element with no condition.
     *
     * @param element element to add
     */
    private void addWithoutCheck(V element) {
        int bucketId = hashAndCompress(element);
        var bucket = buckets.get(bucketId);
        bucket.add(element);
    }
}
