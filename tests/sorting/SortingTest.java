package sorting;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortingTest {
    String[] arr;

    Sorter<String> selectionSort = new InsertionSort<>();
    List<Sorter> sorters = List.of(selectionSort);

    @Test
    void testSortingSame() {
        arr = new String[]{"a", "b", "c"};

        sorters.forEach(sorter -> {
            selectionSort.sort(arr);
            assertArrayEquals(new String[]{"a", "b", "c"}, arr);
        });
    }


    @Test
    void testSortingWorks() {
        arr = new String[]{"b", "c", "a"};

        sorters.forEach(sorter -> {
            selectionSort.sort(arr);
            assertArrayEquals(new String[]{"a", "b", "c"}, arr);
        });
    }

    @Test
    void testSortingWorks2() {
        arr = new String[]{"b", "c", "a", "0"};

        sorters.forEach(sorter -> {
            selectionSort.sort(arr);
            assertArrayEquals(new String[]{"0", "a", "b", "c"}, arr);
        });
    }

    @Test
    void testSortingSameOne() {
        arr = new String[]{"a"};

        sorters.forEach(sorter -> {
            selectionSort.sort(arr);
            assertArrayEquals(new String[]{"a"}, arr);
        });
    }


}
