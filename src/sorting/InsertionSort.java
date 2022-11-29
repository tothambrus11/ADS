package sorting;

public class InsertionSort<E extends Comparable<E>> implements Sorter<E> {
    @Override
    public void sort(E[] array) {
        if(array.length <= 1) return;

        for (int i = 1; i < array.length; i++) {
            E currentElement = array[i];
            int j;
            for (j = i-1; j >= 0; j--) {
                if(currentElement.compareTo(array[j]) >= 0) break;
                array[j+1] = array[j];
            }
            array[j+1] = currentElement;
        }
    }
}

// 1 2 5 4 5