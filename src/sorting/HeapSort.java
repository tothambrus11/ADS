package sorting;

import static sorting.SortUtils.swap;

/**
 * Uses an in-place heap data structure at the beginning of the array to get the maximum elements at
 * every iteration. After moving the value to the right, we need to restore the heap property of the
 * remaining elements on the left.
 */
public class HeapSort<E extends Comparable<E>> implements Sorter<E> {
    @Override
    public void sort(E[] array) {
        heapify(array); // make sure the array is a proper heap in O(n) time

        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i); // Since the array is a max heap, the greatest element is at the root. Place it to the output array section's beginning.
            downHeap(array, 0, i); // We placed a small value to the root, now we need to restore the heap property in O(log(n)) time.
        }
        // Now the size of our heap <= 1, and the rest of the array is sorted too, so we can return.
    }

    private void heapify(E[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            downHeap(array, i, array.length);
        }
    }

    /**
     * Perform an up-heap-bubbling from the given position.
     */
    private void upHeap(E[] array, int pos) {
        while (pos > 0) {
            int parent = (pos - 1) / 2;
            if (array[parent].compareTo(array[pos]) > 0)
                return; // if parent is already greater than this item, stop
            swap(array, pos, parent);
            pos = parent;
        }
    }

    /**
     * Performs down-heap-bubbling from the given position.
     */
    private void downHeap(E[] array, int pos, int heapSize) {
        while (true) {
            int leftChild = pos * 2 + 1;
            int rightChild = pos * 2 + 2;

            int maxIndex = pos;
            if (leftChild < heapSize && array[leftChild].compareTo(array[maxIndex]) > 0) {
                maxIndex = leftChild;
            }
            if (rightChild < heapSize && array[rightChild].compareTo(array[maxIndex]) > 0) {
                maxIndex = rightChild;
            }
            if (maxIndex == pos) return; // no valid bigger child found

            swap(array, maxIndex, pos);
            pos = maxIndex;
        }
    }

}
