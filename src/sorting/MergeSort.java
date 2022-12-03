package sorting;

import java.util.Arrays;

/**
 * A sorting algorithm that recursively sorts copies of subregions of the array, then merges them.
 * @param <E>
 */
public class MergeSort<E extends Comparable<E>> implements Sorter<E> {
    @Override
    public void sort(E[] array) {
        if (array.length <= 1) return; // arrays with 0 and 1 length are already sorted

        // divide
        var firstHalf = Arrays.copyOfRange(array, 0, array.length / 2);
        var secondHalf = Arrays.copyOfRange(array, array.length / 2, array.length);

        // et impera
        sort(firstHalf);
        sort(secondHalf);

        // et laborare... (merging)
        int i = 0, j = 0;
        while (i < firstHalf.length || j < secondHalf.length) {
            if (j == secondHalf.length || (i < firstHalf.length && firstHalf[i].compareTo(secondHalf[j]) <= 0)) {
                array[i + j] = firstHalf[i++];
            } else if (i == firstHalf.length || secondHalf[j].compareTo(firstHalf[i]) <= 0) {
                array[i + j] = secondHalf[j++];
            }
        }
    }

}
