package sorting.quicksort;

import sorting.partitioning.HoarePartition;
import sorting.Sorter;
import sorting.partitioning.LomutoPartition;
import sorting.partitioning.Partition;

import java.util.Random;

/**
 * A quicksort implemented in terms of two general partition functions.
 *
 * @param <E>
 */
public class QuickSortWith2Partitioner<E extends Comparable<E>> implements Sorter<E> {
    private static final Random RANDOM = new Random();
    private final Partition<E> partitioner;

    public QuickSortWith2Partitioner() {
        this.partitioner = new LomutoPartition<>();
    }

    public QuickSortWith2Partitioner(Partition<E> partitioner) {
        this.partitioner = partitioner;
    }

    @Override
    public void sort(E[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(E[] array, final int left, final int right) {
        if (left >= right) return; // range of max 1, already sorted

        int pivotPos = left + RANDOM.nextInt(right - left + 1);
        E pivot = array[pivotPos];

        // partition the array to
        //  - elements < pivot
        //  - elements >= pivot
        int partition2Start = partitioner.partition(array, e -> e.compareTo(pivot) < 0, left, right);

        // partition the second partition to
        //  - elements = pivot
        //  - elements > pivot
        int partition3Start = partitioner.partition(array, e -> e.compareTo(pivot) == 0, partition2Start, right);

        sort(array, left, partition2Start);
        sort(array, partition3Start, right);
    }


}


