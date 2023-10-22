package maps;

import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V> {
    private class KeyIterator implements Iterator<K> {
        private final Iterator<MapEntry<K, V>> entries = entries().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public K next() {
            return entries.next().key();
        }
    }

    private class ValueIterator implements Iterator<V> {
        private final Iterator<MapEntry<K, V>> entries = entries().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public V next() {
            return entries.next().value();
        }
    }

    private class KeyIterable implements Iterable<K>{
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    private class ValueIterable implements Iterable<V> {
        @Override
        public Iterator<V> iterator(){
            return new ValueIterator();
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterable<K> keys() {
        return new KeyIterable();
    }

    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }
}

