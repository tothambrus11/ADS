package sorting;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class QuickSelectTest {
    @Test
    void qsTest1(){
        assertEquals("4", new QuickSelect<String>().select(List.of("5", "4", "1","3", "2"), 4));
    }
    @Test
    void qsTest2(){
        assertEquals("1", new QuickSelect<String>().select(List.of("5", "4", "1","3", "2"), 1));
    }
    @Test
    void qsTest3(){
        assertNull(new QuickSelect<String>().select(List.of("5", "4", "1", "3", "2"), 0));
    }
    @Test
    void qsTest4(){
        assertNull(new QuickSelect<String>().select(List.of("5", "4", "1", "3", "2"), 6));
    }
    @Test
    void qsTest5(){
        assertEquals("2", new QuickSelect<String>().select(List.of("5", "5", "1","1", "2"), 3));
    }
}
