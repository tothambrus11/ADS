package sorting.quickselect;

import sorting.partitioning.LomutoPartition;
import sorting.partitioning.Partition;

import java.util.NoSuchElementException;
import java.util.Random;

public class QuickSelectPartitioning<V extends Comparable<V>> implements SelectNthElement<V> {
    private static final Random RANDOM = new Random();
    private final Partition<V> partitioner;

    public QuickSelectPartitioning(Partition<V> partitioner) {
        this.partitioner = partitioner;
    }

    public QuickSelectPartitioning() {
        this.partitioner = new LomutoPartition<>();
    }

    /**
     * Returns the kth smallest element from the list
     *
     * @param array
     * @param k
     */
    public V select(V[] array, int k) {
        return select(array, 0, array.length - 1, k);
    }

    public V select(V[] array, int left, int right, int k) {
        if (k <= 0 || k > right - left + 1) throw new NoSuchElementException();

        V pivot = array[left + RANDOM.nextInt(right - left + 1)];

        int secondPartitionStart = partitioner.partition(array, x -> x.compareTo(pivot) < 0, left, right);
        if (k <= secondPartitionStart - left) {
            return select(array, left, secondPartitionStart - 1, k);
        }
        int thirdPartitionStart = partitioner.partition(array, x -> x.compareTo(pivot) == 0, secondPartitionStart, right);
        if (k <= thirdPartitionStart - left) {
            return pivot;
        }
        return select(array, thirdPartitionStart, right, k - (thirdPartitionStart - left));
    }
}
