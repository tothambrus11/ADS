package sorting;

/**
 * A sorter algorithm that builds a sorted array by selecting the lowest element from the unsorted
 * part of the list n times, and swapping it with the element after the sorted section of the list.
 *
 * @param <E>
 */
public class SelectionSort<E extends Comparable<E>> implements Sorter<E> {
    @Override
    public void sort(E[] array) {
        for (int i = 0; i < array.length-1; i++) {
            E minValue = array[i];
            int minIndex = i;
            for (int j = i+1; j < array.length; j++) {
                if(array[j].compareTo(minValue) < 0){
                    minValue = array[j];
                    minIndex = j;
                }
            }
            E temp = array[i];
            array[i] = minValue;
            array[minIndex] = temp;
        }

    }
}
