package sets;

import java.util.Collection;

public interface Set<V> extends Collection<V> {
    /**
     * Adds the value to the set if it is not already present.
     *
     * @param value the value to be added
     * @return false if the value was already in the set and nothing has been added
     */
    boolean add(V value);

    /**
     * Removes the value from the set if it is there, otherwise returns false
     *
     * @param value the value to be removed
     * @return true if the value has been removed
     */
    boolean remove(Object value);

    /**
     * Returns true if the value is an element of the set
     * @param value the value to check whether it's an element of the set
     * @return true if the value is an element of the set
     */
    boolean contains(Object value);

    boolean isEmpty();

    int size();

    /**
     * Removes all the elements from the set.
     */
    void clear();

    boolean retainAll(Collection<?> elementsToRetain);

    /**
     * Adds all the elements from the given sequence to the set. S ∪= other
     * @param elements elements to add
     */
    default void addAll(Iterable<V> elements) {
        for (var e : elements) add(e);
    }


    /**
     * Removes all elements from this set that are in the given set. S \= other
     * @param elements elements to remove
     */
    default void removeAll(Iterable<V> elements) {
        for(var e : elements) remove(e);
    }

    Object[] toArray();

    <T> T[] toArray(T[] a);


}
