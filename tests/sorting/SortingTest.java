package sorting;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortingTest {
    String[] arr;
    List<Sorter<String>> sorters = List.of(
            new SelectionSort<>(),
            new InsertionSort<>(),
            new HeapSort<>(),
            new MergeSort<>()
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


}
