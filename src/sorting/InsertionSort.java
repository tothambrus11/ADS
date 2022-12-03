package sorting;

/**
 * A soring algorithm that builds a sorted list on the left side of the array. At every iteration,
 * the algorithm takes the next element after the sorted range of the array, and finds its position
 * inside the sorted range. Then everything is shifted towards the right beginning from the target
 * position, and the value is inserted into the 'gap'.
 *
 * @param <E> the comparable element's type
 */
public class InsertionSort<E extends Comparable<E>> implements Sorter<E> {
    @Override
    public void sort(E[] array) {
        for (int i = 1; i < array.length; i++) {
            E currentElement = array[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (currentElement.compareTo(array[j]) >= 0) break;
                array[j + 1] = array[j];
            }
            array[j + 1] = currentElement;
        }
    }
}

// 1 2 5 4 5