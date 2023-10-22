package maps;

import java.util.*;
import java.util.stream.StreamSupport;

/**
 * A hashmap implementation using separate chaining for collision handling. This means for every
 * possible compressed hash value, we have a bucket (list) that contains all the entries with that
 * compressed hash of the key.
 *
 * @param <K> type of the key
 * @param <V> type of the value
 */
public class ChainHashMap<K, V> extends AbstractMap<K, V> {
    private static final int BIG_PRIME = 268435649; // we support hashmaps with max 2^24 elements, this prime is around 2^28
    private static final Random RANDOM = new Random();
    private final int hashCompressA = RANDOM.nextInt(BIG_PRIME - 1) + 1; // [1, p-1]
    private final int hashCompressB = RANDOM.nextInt(BIG_PRIME); // [0, p-1]

    int size = 0;
    ArrayList<ArrayList<MapEntry<K, V>>> buckets = new ArrayList<>();

    ChainHashMap() {
        buckets.ensureCapacity(8);
        for (int i = 0; i < 8; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    public int bucketCount() {
        return buckets.size();
    }

    public float loadFactor() {
        return (float) size / bucketCount();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        int bucketId = hashAndCompress(key);
        var bucket = buckets.get(bucketId);

        return bucket.stream()
                .filter(e -> Objects.equals(e.key(), key))
                .findFirst().map(MapEntry::value)
                .orElse(null);
    }

    @Override
    public boolean containsKey(K key) {
        int bucketId = hashAndCompress(key);
        var bucket = buckets.get(bucketId);

        return bucket.stream()
                .anyMatch(e -> Objects.equals(e.key(), key));
    }

    @Override
    public V removeKey(K key) {
        int bucketId = hashAndCompress(key);
        var bucket = buckets.get(bucketId);

        var exisingEntry = bucket.stream()
                .filter(e -> Objects.equals(e.key(), key))
                .findFirst();

        exisingEntry.ifPresent(bucket::remove);

        if (exisingEntry.isPresent()) {
            bucket.remove(exisingEntry.get());
            size--;

            if (loadFactor() <= 0.25) resizeDown();
        }

        return exisingEntry.map(MapEntry::value).orElse(null);
    }

    @Override
    public V put(K key, V value) {
        return put(new MapEntry<>(key, value));
    }

    private void resizeDown() {
        if(bucketCount() / 2 < 8) return;

        System.out.println("--------------------------------------------");
        System.out.println("Resizing ↓ (size: " + size + "   bucket count: " + bucketCount() + "   load factor: " + Math.round(1000 * loadFactor()) / 10 + "%)");
        System.out.println("--------------------------------------------");

        resizeAndCopy(bucketCount() / 2);
    }

    private void resizeAndCopy(int N) {
        var buffer = new ArrayList<MapEntry<K, V>>();
        for (var e : entries()) {
            buffer.add(e);
        }
        buckets.clear();
        size = 0;

        buckets.ensureCapacity(N);
        for (int i = 0; i < N; i++) {
            buckets.add(new ArrayList<>());
        }

        for (var e : buffer) {
            put(e);
        }
    }

    private void resizeUp() {
        System.out.println("--------------------------------------------");
        System.out.println("Resizing ↑ (size: " + size + "   bucket count: " + bucketCount() + "   load factor: " + Math.round(1000 * loadFactor()) / 10 + "%)");
        System.out.println("--------------------------------------------");

        resizeAndCopy(bucketCount() * 2);
    }

    private V put(MapEntry<K, V> entry) {
        int bucketId = hashAndCompress(entry.key());
        var bucket = buckets.get(bucketId);

        var exisingEntry = bucket.stream()
                .filter(e -> Objects.equals(e.key(), entry.key()))
                .findFirst();

        exisingEntry.ifPresent(bucket::remove);
        if (exisingEntry.isPresent()) {
            bucket.remove(exisingEntry.get());
        } else {
            size++; // increase the size only if we didn't need to remove anything
        }

        bucket.add(entry);

        if (loadFactor() >= 0.75) {
            resizeUp();
        }

        return exisingEntry.map(MapEntry::value).orElse(null);
    }

    /**
     * Hashes the given key and compresses it using the Multiply-Add-and-Divide method.
     *
     * @param key the key to hash and compress
     * @return (( a * h + b) % p) % N   where a and b are random constants, p is a prime way bigger than N, and N is the number of buckets
     */
    private int hashAndCompress(K key) {
        return Math.abs(((hashCompressA * Objects.hashCode(key)) + hashCompressB) % BIG_PRIME) % bucketCount();
    }

    @Override
    public Iterable<MapEntry<K, V>> entries() {
        return buckets.stream()
                .flatMap(Collection::stream)
                .toList();
    }
}
