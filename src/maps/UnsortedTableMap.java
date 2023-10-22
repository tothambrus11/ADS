package maps;

import java.util.*;

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
    private final List<MapEntry<K, V>> entries = new ArrayList<>();

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public V get(K key) {
        for (var e : entries) {
            if (Objects.equals(e.key(), key)) {
                return e.value();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        for (int i = 0; i < entries.size(); i++) {
            if (Objects.equals(key, entries.get(i).key())) {
                V previousValue = entries.get(i).value();
                entries.set(i, new MapEntry<>(key, value));
                return previousValue;
            }
        }
        entries.add(new MapEntry<>(key, value));
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return entries
                .stream()
                .anyMatch(e->Objects.equals(e.key(), key));
    }

    @Override
    public V removeKey(K key) {
        var removed = entries.stream().filter(e -> Objects.equals(e.key(), key)).findFirst();
        removed.ifPresent(entries::remove);
        return removed.map(MapEntry::value).orElse(null);
    }

    @Override
    public Iterable<MapEntry<K, V>> entries() {
        return entries;
    }
}
