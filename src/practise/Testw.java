package practise;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Testw{

    @Test
    public void testEmpty() {
        String[][] data = {};
        String[][] data2 = {};
        Solution.stableSort(data, 0);
        assertArrayEquals(data2, data);
    }

    @Test
    public void testOneColumn() {
        String[][] data = {{"d"}, {"a"}, {"e"}, {"b"}, {"g"}, {"c"}, {"f"}};
        String[][] data2 = {{"a"}, {"b"}, {"c"}, {"d"}, {"e"}, {"f"}, {"g"}};
        Solution.stableSort(data, 0);
        assertArrayEquals(data2, data);
    }
    @Test
    public void testOneColum2n() {
        String[][] data = {{"1"}, {"2"}, {"10"},{"20"}};
        String[][] data2 = {{"1"}, {"10"}, {"2"}, {"20"}};
        Solution.stableSort(data, 0);
        assertEquals(Arrays.toString(data2[0]), Arrays.toString(data[0]));
    }

    @Test
    public void testStability() {
        System.out.println("---------");

        String[][] data = {{"1", "1"}, {"1", "2"}, {"2", "2"},{"2", "1"}};
        String[][] data2 = {{"1", "1"}, {"1", "2"}, {"2", "2"},{"2", "1"}};
        Solution.stableSort(data, 0);
        System.out.println("---------");

        assertEquals(Arrays.toString(data2[0]), Arrays.toString(data[0]));

        System.out.println("---------");

    }

    @Test
    public void testMixed() {
        String[][] data = {{"aaa", "ddd"}, {"ccc", "bbb"}};
        String[][] data2 = {{"aaa", "ddd"}, {"ccc", "bbb"}};
        String[][] data3 = {{"ccc", "bbb"}, {"aaa", "ddd"}};
        Solution.stableSort(data, 0);
        assertArrayEquals(data2, data);
        Solution.stableSort(data, 1);
        assertArrayEquals(data3, data);
    }
}
