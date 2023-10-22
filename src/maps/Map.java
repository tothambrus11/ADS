package maps;

public interface Map<K, V> {
    int size();

    boolean isEmpty();

    V get(K key);

    /**
     * Returns true when the map contains the given key
     * @param key the key to check
     * @return true iff the map contains a key equivalent to the given key
     */
    boolean containsKey(K key);

    /**
     * Removes the given key from the map if present and returns the removed value
     * @param key the key to remove from the map
     * @return the remove value if present, otherwise null
     */
    V removeKey(K key);

    /**
     * Adds/replaces a key in the map. The old value is returned if present, otherwise null.
     * @param key the key to insert the value at
     * @param value the value to insert at the key
     * @return the old value at the key when present, otherwise null
     */
    V put(K key, V value);

    Iterable<K> keys();

    Iterable<V> values();

    Iterable<MapEntry<K, V>> entries();

    record MapEntry<K, V>(K key, V value) {
    }

}
