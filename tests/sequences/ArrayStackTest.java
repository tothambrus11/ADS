package sequences;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {
    @Test
    void testSize0() {
        var as = new ArrayStack<>(100, String.class);
        assertSame(0, as.size());
    }

    @Test
    void testSize1() {
        var as = new ArrayStack<>(100, String.class);
        as.push("hello");
        assertSame(1, as.size());
    }

    @Test
    void testPushTop() {
        var as = new ArrayStack<>(100, String.class);
        as.push("hello");
        assertEquals("hello", as.top());
    }

    @Test
    void testPop() {
        var as = new ArrayStack<>(100, String.class);
        assertNull(as.pop());
        assertTrue(as.isEmpty());
    }

    @Test
    void testPushPop() {
        var as = new ArrayStack<>(100, String.class);
        as.push("Hello");
        as.push("world");

        assertSame(2, as.size());
        assertFalse(as.isEmpty());
        assertEquals("world", as.pop());
        assertEquals("Hello", as.pop());
    }
}