package sorting.partitioning;

import java.util.function.Predicate;

import static sorting.SortUtils.swap;

public class LomutoPartition<V> implements Partition<V> {
    @Override
    public int partition(V[] array, Predicate<V> inFirstPartition) {
        return partition(array, inFirstPartition, 0, array.length - 1);
    }

    @Override
    public int partition(V[] array, Predicate<V> inFirstPartition, int left, int right) {
        int secondPartitionStart = left;
        for (int i = left; i <= right; i++) {
            if (inFirstPartition.test(array[i])) {
                swap(array, secondPartitionStart++, i);
            }
        }
        return secondPartitionStart;
    }
}
