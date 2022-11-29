package sequences;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayQueueTest {
    @Test
    void constructorTest() {
        var q = new ArrayQueue<>(5, String.class);
        assertEquals(0, q.size());
        assertTrue(q.isEmpty());
    }

    @Test
    void enqTest() {
        var q = new ArrayQueue<>(5, String.class);
        q.enqueue("Hello");
        assertEquals(1, q.size());
        assertFalse(q.isEmpty());
        q.enqueue("world");
        assertEquals(2, q.size());
        assertFalse(q.isEmpty());
    }

    @Test
    void deqTest() {
        var q = new ArrayQueue<>(5, String.class);
        q.enqueue("Hello");
        q.enqueue("world");

        assertEquals("Hello", q.dequeue());
        assertEquals(1, q.size());
        assertEquals("world", q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    void longStuff() {
        var q = new ArrayQueue<>(5, String.class);
        q.enqueue("Hello 1");
        q.enqueue("Hello 2");
        q.enqueue("Hello 3");
        q.enqueue("Hello 4");
        q.enqueue("Hello 5");
        q.dequeue();
        q.enqueue("Hello 6");
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        assertEquals("Hello 6", q.dequeue());
    }


    @Test
    void expandSize() {
        var q = new ArrayQueue<>(4, String.class);
        q.enqueue("1");
        q.enqueue("2");
        q.enqueue("3");
        q.enqueue("4");
        q.enqueue("5");
        assertEquals("1", q.first());
        assertEquals("5", q.get(4));
        assertEquals("1,2,3,4,5", q.toString());
    }

    @Test
    void firstTest() {
        var q = new ArrayQueue<>(5, String.class);
        assertThrows(NoSuchElementException.class, q::first);

        q.enqueue("hello");
        assertEquals("hello", q.first());
    }
}
