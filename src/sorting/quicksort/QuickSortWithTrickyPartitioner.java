package sorting.quicksort;

import sorting.partitioning.HoarePartition;
import sorting.Sorter;
import sorting.partitioning.LomutoPartition;
import sorting.partitioning.Partition;

import java.util.Random;

public class QuickSortWithTrickyPartitioner<E extends Comparable<E>> implements Sorter<E> {
    private static final Random RANDOM = new Random();

    private final Partition<E> partitioner;

    public QuickSortWithTrickyPartitioner() {
        this.partitioner = new LomutoPartition<>();
    }

    public QuickSortWithTrickyPartitioner(Partition<E> partitioner) {
        this.partitioner = partitioner;
    }

    @Override
    public void sort(E[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(E[] array, final int left, final int right) {
        if (left >= right) return; // range of max 1, already sorted

        int pivotPos = left + RANDOM.nextInt(right - left + 1);

        // Use the pivot position returned by the partition function
        pivotPos = trickyPartition(array, left, right, pivotPos);

        // Recursively sort the left and right sub-arrays
        sort(array, left, pivotPos - 1);
        sort(array, pivotPos + 1, right);
    }

    private int trickyPartition(E[] array, int left, int right, int pivotPos) {
        // Save pivot value
        E pivot = array[pivotPos];

        // Swap pivot with last element
        swap(array, pivotPos, right);

        // Partition array without last element
        pivotPos = partitioner.partition(array, e -> e.compareTo(pivot) < 0, left, right - 1);

        // Swap pivot from last pos to its final place
        swap(array, right, pivotPos);

        return pivotPos;
    }

    private void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


