package maps;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {
    @Test
    public void mapTestPutRemove() {
        List<Map<String, String>> maps = List.of(
                new UnsortedTableMap<>(),
                new ChainHashMap<>()
        );

        for (Map<String, String> m : maps) {
            assertEquals(0, m.size());
            assertTrue(m.isEmpty());
            assertFalse(m.containsKey("hello"));
            assertFalse(m.containsKey(null));

            m.put("lego", "asd");
            assertEquals(1, m.size());
            assertFalse(m.isEmpty());
            assertEquals("asd", m.get("lego"));
            assertFalse(m.containsKey(null));
            assertFalse(m.containsKey("sddasdasd"));
            assertTrue(m.containsKey("lego"));


            m.put("lego", "world");
            assertEquals(1, m.size());
            assertTrue(m.containsKey("lego"));
            assertFalse(m.isEmpty());
            assertEquals("world", m.get("lego"));


            m.put("cactus", "42");
            assertEquals(2, m.size());
            assertFalse(m.isEmpty());

            assertEquals("42", m.get("cactus"));
            assertEquals("world", m.get("lego"));

            assertNull(m.removeKey("asdasdasdasd"));

            assertEquals(2, m.size());
            assertFalse(m.isEmpty());

            assertEquals("42", m.get("cactus"));
            assertEquals("world", m.get("lego"));

            assertEquals("42", m.removeKey("cactus"));
            assertEquals(1, m.size());
            assertFalse(m.isEmpty());
        }
    }

    @Test
    public void chainHashLoadFactorTestResize() {
        var map = new ChainHashMap<String, Integer>();
        for (int i = 0; i < 1000; i++) {
            map.put(i + "", 1000 - i);
            System.out.println(Math.round(map.loadFactor() * 1000) / 10);
            assertTrue(map.loadFactor() < 0.75);
            assertTrue(map.loadFactor() > 0.25 || map.bucketCount() <= 8);
        }

        System.out.println("======================================================================");
        System.out.println("======================================================================");
        System.out.println("======================================================================");
        System.out.println("======================================================================");

        for (int i = 0; i < 1000; i++) {
            map.removeKey(i + "");
            System.out.println(Math.round(map.loadFactor() * 1000) / 10);
            assertTrue(map.loadFactor() < 0.75);
            assertTrue(map.loadFactor() > 0.25 || map.bucketCount() <= 8);
        }
    }
}
