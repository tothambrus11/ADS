package sets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SetTest {

    @Test
    public void testAddAndContains() {
        Set<String> S = new HashSet<>();
        assertEquals(0, S.size());
        assertTrue(S.isEmpty());
        for (var e : S) {
            throw new AssertionError("iterator should have stopped");
        }

        assertTrue(S.add("hello"));
        assertEquals(1, S.size());
        assertFalse(S.isEmpty());
        assertTrue(S.contains("hello"));
        assertFalse(S.contains("hell"));
        for (var e : S) assertEquals("hello", e);

        assertFalse(S.add("hello"));
        assertEquals(1, S.size());
        assertFalse(S.isEmpty());
        assertFalse(S.contains("hell"));
        for (var e : S) assertEquals("hello", e);

        assertTrue(S.add("asdd"));
        assertEquals(2, S.size());
        assertFalse(S.isEmpty());
        assertTrue(S.contains("hello"));
        assertTrue(S.contains("asdd"));
        assertFalse(S.contains("hell"));
        assertFalse(S.contains("adsas"));

        int n = -1;
        for (var e : S) {
            if (n == -1) {
                if (e.equals("hello")) {
                    n = 0;
                } else if (e.equals("asdd")) {
                    n = 1;
                } else {
                    throw new AssertionError("should have been hello or asdd");
                }
            } else if (n == 0) {
                assertEquals("asdd", e);
                n = 111;
            } else if (n == 1) {
                assertEquals("hello", e);
                n = 111;
            } else {
                throw new AssertionError("iterator should have stopped");
            }
        }

    }

    @Test
    public void hashsetLoadFactorTest() {
        var S = new HashSet<String>();

        assertTrue(S.loadFactor() < 0.75 && (S.loadFactor() > 0.25 || S.bucketCount() <= HashSet.MIN_CAPACITY));
        for (int i = 0; i < 1000; i++) {
            S.add(i + "");
            assertEquals(i+1, S.size());
            assertTrue(S.loadFactor() < 0.75 && (S.loadFactor() > 0.25 || S.bucketCount() <= HashSet.MIN_CAPACITY));
        }

        for (int i = 0; i < 1000; i++) {
            S.remove(i + "");
            assertEquals(i+1, S.size());
            assertTrue(S.loadFactor() < 0.75 && (S.loadFactor() > 0.25 || S.bucketCount() <= HashSet.MIN_CAPACITY));
        }
    }
}
