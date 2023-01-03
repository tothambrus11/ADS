package sorting;

import org.junit.jupiter.api.Test;
import sorting.partitioning.HoarePartition;
import sorting.partitioning.LomutoPartition;
import sorting.quickselect.QuickSelectLazy;
import sorting.quickselect.QuickSelectPartitioning;
import sorting.quickselect.SelectNthElement;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSelectTest {
    List<SelectNthElement<String>> selectors = List.of(
            new QuickSelectPartitioning<String>(new LomutoPartition<>()),
            new QuickSelectPartitioning<String>(new HoarePartition<>()),
            new QuickSelectLazy<String>()
    );

    @Test
    void qsTest1() {
        selectors.forEach(selector -> {
            String[] array = new String[]{"5", "4", "1", "3", "2"};
            assertEquals("4", selector.select(array, 4));
        });
    }

    @Test
    void qsTest2() {
        selectors.forEach(selector -> {
            String[] array = new String[]{"5", "4", "1", "3", "2"};
            assertEquals("1", selector.select(array, 1));
        });
    }
    @Test
    void qsTest6() {
        selectors.forEach(selector -> {
            String[] array = new String[]{"5", "4"};
            assertEquals("5", selector.select(array, 2));
        });
    }   @Test
    void qsTest10() {
        selectors.forEach(selector -> {
            String[] array = new String[]{"3", "4"};
            assertEquals("4", selector.select(array, 2));
        });
    }
   @Test
    void qsTest11() {
        selectors.forEach(selector -> {
            String[] array = new String[]{"4", "3", "2"};
            assertEquals("4", selector.select(array, 3));
        });
    }
    @Test
    void qsTest7() {
        selectors.forEach(selector -> {
            String[] array = new String[]{"5", "4"};
            assertEquals("4", selector.select(array, 1));
        });
    }

    @Test
    void qsTest8() {
        selectors.forEach(selector -> {
            String[] array = new String[]{"5"};
            assertEquals("5", selector.select(array, 1));
        });
    }
    @Test
    void qsTest9() {
        selectors.forEach(selector -> {
            assertThrows(NoSuchElementException.class, ()->{
                String[] array = new String[]{"5"};
                selector.select(array, 2);
            });
            assertThrows(NoSuchElementException.class, ()->{
                String[] array = new String[]{"5"};
                selector.select(array, 0);
            });
        });
    }

    @Test
    void qsTest3() {
        selectors.forEach(selector -> {
            assertThrows(NoSuchElementException.class, ()->{
                String[] array = new String[]{"5", "4", "1", "3", "2"};
                selector.select(array, 0);
            });
        });
    }

    @Test
    void qsTest4() {
        selectors.forEach(selector -> {
            assertThrows(NoSuchElementException.class, ()->{
                String[] array = new String[]{"5", "4", "1", "3", "2"};
                selector.select(array, 6);
            });
        });
    }

    @Test
    void qsTest5() {
        selectors.forEach(selector -> {
            String[] array = new String[]{"5", "5", "1", "1", "2"};
            assertEquals("2", selector.select(array, 3));
        });
    }
}
