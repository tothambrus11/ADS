package sequences;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedStackTest {

    @Test
    void testSize0() {
        var as = new ArrayStack<>(100, String.class);
        assertSame(0, as.size());
    }

    @Test
    void testSize1() {
        var as = new LinkedStack<String>();
        as.push("hello");
        assertSame(1, as.size());
    }

    @Test
    void testPushTop() {
        var as = new LinkedStack<String>();
        as.push("hello");
        assertEquals("hello", as.top());
    }

    @Test
    void testPop() {
        var as = new LinkedStack<String>();
        assertNull(as.pop());
        assertTrue(as.isEmpty());
    }

    @Test
    void testPushPop() {
        var as = new LinkedStack<String>();
        as.push("Hello");
        as.push("world");

        assertSame(2, as.size());
        assertFalse(as.isEmpty());
        assertEquals("world", as.pop());
        assertEquals("Hello", as.pop());
    }
}
