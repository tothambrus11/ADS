package sorting;

import org.junit.jupiter.api.Test;
import sorting.partitioning.HoarePartition;
import sorting.partitioning.LomutoPartition;
import sorting.quicksort.QuickSort;
import sorting.quicksort.QuickSortWith2Partitioner;
import sorting.quicksort.QuickSortWithTrickyPartitioner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortingTest {
    String[] arr;
    List<Sorter<String>> sorters = List.of(
            new SelectionSort<>(),
            new InsertionSort<>(),
            new HeapSort<>(),
            new MergeSort<>(),
            new QuickSort<>(),
            new QuickSortWithTrickyPartitioner<>(new LomutoPartition<String>()),
            new QuickSortWithTrickyPartitioner<>(new HoarePartition<String>()),
            new QuickSortWith2Partitioner<>(new LomutoPartition<String>()),
            new QuickSortWith2Partitioner<>(new HoarePartition<String>())
    );

    @Test
    void testSortingSame() {
        sorters.forEach(sorter -> {
            arr = new String[]{"a", "b", "c"};
            sorter.sort(arr);
            assertArrayEquals(new String[]{"a", "b", "c"}, arr);
        });
    }


    @Test
    void testSortingWorks() {

        sorters.forEach(sorter -> {
            arr = new String[]{"b", "c", "a"};
            sorter.sort(arr);
            assertArrayEquals(new String[]{"a", "b", "c"}, arr);
        });
    }

    @Test
    void testSortingWorks3() {
        sorters.forEach(sorter -> {
            arr = new String[]{"2", "3", "1"};
            sorter.sort(arr);
            assertArrayEquals(new String[]{"1", "2", "3"}, arr);
        });
    }

    @Test
    void testSortingWorks2() {
        sorters.forEach(sorter -> {
            arr = new String[]{"b", "c", "a", "0"};
            sorter.sort(arr);
            assertArrayEquals(new String[]{"0", "a", "b", "c"}, arr);
        });
    }

    @Test
    void testSortingWorks10() {
        sorters.forEach(sorter -> {
            arr = new String[]{"6", "5", "9", "2", "3", "7", "0", "1", "8", "4",};
            sorter.sort(arr);
            assertArrayEquals(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}, arr);
        });
    }

    @Test
    void testSortingSameOne() {

        sorters.forEach(sorter -> {
            arr = new String[]{"a"};
            sorter.sort(arr);
            assertArrayEquals(new String[]{"a"}, arr);
        });
    }

    @Test
    void testSortingZero() {
        sorters.forEach(sorter -> {
            arr = new String[]{};
            sorter.sort(arr);
            assertArrayEquals(new String[]{}, arr);
        });
    }

    @Test
    void testSortingHeap() {
        sorters.forEach(sorter -> {
            arr = new String[]{"4", "7", "c", "9", "1", "3"};
            sorter.sort(arr);
            assertArrayEquals(new String[]{"1", "3", "4", "7", "9", "c"}, arr);
        });
    }


    @Test
    void testSortingPartitionQQuick() {
        sorters.forEach(sorter -> {
            arr = new String[]{"7", "4", "3","5"};
            sorter.sort(arr);
            assertArrayEquals(new String[]{"3", "4","5", "7"}, arr);
        });
    }

    @Test
    void testSortingSameValues() {
        sorters.forEach(sorter -> {
            arr = new String[]{"1", "1", "1", "1", "1", "1"};
            sorter.sort(arr);
            assertArrayEquals(new String[]{"1", "1", "1", "1", "1", "1"}, arr);
        });
    }

    @Test
    void testSortingSameValuesOther() {
        sorters.forEach(sorter -> {
            arr = new String[]{"1", "1", "1", "1", "1"};
            sorter.sort(arr);
            assertArrayEquals(new String[]{"1", "1", "1", "1", "1"}, arr);
        });
    }
    @Test
    void testSortingSameValuesOther2() {
        sorters.forEach(sorter -> {
            arr = new String[]{"1", "1"};
            sorter.sort(arr);
            assertArrayEquals(new String[]{"1", "1"}, arr);
        });
    }

    @Test
    void testSortingQS() {
        sorters.forEach(sorter -> {
            arr = new String[]{"11", "90", "33", "71", "24", "50", "35", "30", "15", "21"};
            sorter.sort(arr);
            assertArrayEquals(new String[]{
                    "11",
                    "15",
                    "21",
                    "24",
                    "30",
                    "33",
                    "35",
                    "50",
                    "71",
                    "90",
            }, arr);
        });
    }


}
