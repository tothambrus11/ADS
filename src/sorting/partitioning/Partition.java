package sorting.partitioning;

import java.util.function.Predicate;

public interface Partition<V> {
    int partition(V[] array, Predicate<V> inFirstPartition);

    int partition(V[] array, Predicate<V> inFirstPartition, int left, int right);

}
