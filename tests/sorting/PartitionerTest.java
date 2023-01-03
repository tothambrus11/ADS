package sorting;

import org.junit.jupiter.api.Test;
import sorting.partitioning.HoarePartition;
import sorting.partitioning.LomutoPartition;
import sorting.partitioning.Partition;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class PartitionerTest {

    List<Partition<Integer>> partitionList = List.of(
            new HoarePartition<>(),
            new LomutoPartition<>()
    );

    @Test
    public void evenOdd1() {
        partitionList.forEach(p -> {
            testPartitioning(3, new Integer[]{1, 2, 3, 4, 5, 6}, x -> x % 2 == 0, p);
        });
    }

    @Test
    public void evenOdd2() {
        partitionList.forEach(p -> {
            testPartitioning(2, new Integer[]{1, 2, 3, 4, 5}, x -> x % 2 == 0, p);
        });
    }

    @Test
    public void evenOdd3() {
        partitionList.forEach(p -> {
            testPartitioning(1, new Integer[]{1, 2}, x -> x % 2 == 0, p);
        });
    }

    @Test
    public void evenOddOneElement() {
        partitionList.forEach(p -> {
            testPartitioning(1, new Integer[]{4}, x -> x % 2 == 0, p);
        });
    }


    @Test
    public void weirdBehaving() {
        partitionList.forEach(p -> {
            testPartitioning(2, new Integer[]{2,3,1,0}, x -> x < 2, p);
        });
    }

    @Test
    public void weirdBehaving2() {
        partitionList.forEach(p -> {
            testPartitioning(3, new Integer[]{2, 3, 1, 0}, x -> x <= 2, p);
        });
    }

    @Test
    public void gptTest1() {
        partitionList.forEach(p -> {
            testPartitioning(0, new Integer[]{5}, x -> x < 2, p);
        });
    }

    @Test
    public void testPartition() {
        partitionList.forEach(p -> {

            // Test with array of length 1
            testPartitioning(1, new Integer[]{5}, x -> x < 10, p);
            testPartitioning(1, new Integer[]{5, 7}, x -> x < 6, p);

            // Test with array of length 3
            testPartitioning(1, new Integer[]{5, 3, 7}, x -> x < 4, p);
            testPartitioning(1, new Integer[]{3, 5, 7}, x -> x < 4, p);
            testPartitioning(1, new Integer[]{7, 5, 3}, x -> x < 4, p);
            testPartitioning(0, new Integer[]{7, 5, 3}, x -> x < 1, p);

            // Test with array of length 4
            testPartitioning(1, new Integer[]{5, 3, 7, 4}, x -> x < 4, p);
            testPartitioning(3, new Integer[]{5,3,7,4}, x -> x < 6, p);
            testPartitioning(1, new Integer[]{5,3,7,4}, x -> x >= 6, p);

        });
    }

    @Test
    public void gptTest2() {
        partitionList.forEach(p -> {
            var list = new Integer[]{5, 7};
            int pivot = 5;
            int pivotIndex = p.partition(list, v -> v.compareTo(pivot) < 0, 0, 1);

            assertArrayEquals(new Integer[]{5, 7}, list);
            assertEquals(0, pivotIndex);
        });
    }

    @Test
    public void gptTest3() {
        partitionList.forEach(p -> {
            testPartitioning(1, new Integer[]{5, 7}, x -> x < 7, p);
        });
    }

    @Test
    public void testIdk() {
        partitionList.forEach(p -> {
            testPartitioning(1, new Integer[]{5, 7}, x -> x < 6, p);
        });
    }

    void testPartitioning(int secondPartitionStart, Integer[] array,
                          Predicate<Integer> isInFirstPartition, Partition<Integer> partitioner) {
        assertEquals(secondPartitionStart, partitioner.partition(array, isInFirstPartition));

        for (int i = 0; i < secondPartitionStart; i++) {
            assertTrue(isInFirstPartition.test(array[i]));
        }

        for (int i = secondPartitionStart; i < array.length; i++) {
            assertFalse(isInFirstPartition.test(array[i]));
        }
    }
}
