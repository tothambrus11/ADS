package sorting.partitioning;

import java.util.Arrays;
import java.util.function.Predicate;

import static sorting.SortUtils.swap;

public class HoarePartition<V> implements Partition<V>{
    /**
     * @param array            the array to partition
     * @param inFirstPartition a predicate that returns true if an element should be in the first partition
     * @return start of 2nd partition
     */
    public int partition(V[] array, Predicate<V> inFirstPartition) {
        return partition(array, inFirstPartition, 0, array.length - 1);
    }

    /**
     * Partitions the array in a certain range based on the provided unary predicate
     * @param array the array to partition a subsection in
     * @param inFirstPartition a predicate that returns true if an element should be in the first partition
     * @param left the start index of the range to partition (inclusive)
     * @param right the end index of the range to partition (inclusive)
     * @return the start of the second partition
     */
    public int partition(V[] array, Predicate<V> inFirstPartition, int left, int right) {
        int p1 = left;
        int p2 = right;

        while (p1 <= p2) {
            // search for next wrong item on the left
            while (p1 <= p2 && inFirstPartition.test(array[p1])) p1++;

            // search for next wrong item on the right
            while (p1 <= p2 && !inFirstPartition.test(array[p2])) p2--;

            // If p1 hasn't crossed p2, swap the elements at the p1 and p2
            if (p1 <= p2) {
                swap(array, p1, p2);
                p1++;
                p2--;
            }
        }

        return p1;
    }

}
