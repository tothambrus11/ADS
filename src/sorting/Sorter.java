package sorting;

public interface Sorter<E extends Comparable<E>> {
    void sort(E[] array);
}
