package sequences;

import org.junit.jupiter.api.Test;
import sequences.DoublyLinkedList;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    @Test
    void testConstructor() {
        DoublyLinkedList<String> l = new DoublyLinkedList<>();
        assertThrows(NoSuchElementException.class, l::first);
        assertThrows(NoSuchElementException.class, l::last);
    }

    @Test
    void linkFirstTest() {
        DoublyLinkedList<String> l = new DoublyLinkedList<>();
        l.linkFirst("Hello");
        assertEquals(1, l.size());
        assertEquals("Hello", l.first());
        assertEquals("Hello", l.last());
        l.linkFirst("world");
        assertEquals("world", l.first());
        assertEquals("Hello", l.last());
        assertEquals(2, l.size());
    }

    @Test
    void linkLastTest() {
        DoublyLinkedList<String> l = new DoublyLinkedList<>();
        l.linkLast("Hello");
        assertEquals(1, l.size());
        assertEquals("Hello", l.first());
        assertEquals("Hello", l.last());
        l.linkLast("world");
        assertEquals(2, l.size());
        assertEquals("world", l.last());
        assertEquals("Hello", l.first());
    }

    @Test
    void getTest() {
        DoublyLinkedList<String> l = new DoublyLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, ()->l.get(0));
        assertThrows(IndexOutOfBoundsException.class, ()->l.get(1));
        assertThrows(IndexOutOfBoundsException.class, ()->l.get(2));

        l.linkLast("1");
        assertEquals("1", l.get(0));
        assertThrows(IndexOutOfBoundsException.class, ()->l.get(1));
        assertThrows(IndexOutOfBoundsException.class, ()->l.get(2));

        l.linkLast("2");

        l.linkLast("3");
        assertEquals("1", l.get(0));
        assertEquals("2", l.get(1));
        assertEquals("3", l.get(2));
    }

    @Test
    void indexOfTest() {
        DoublyLinkedList<String> l = new DoublyLinkedList<>();
        assertEquals(-1, l.indexOf(null));
        assertEquals(-1, l.indexOf("Hello"));

        l.linkLast("Hello");
        assertEquals(0, l.indexOf("Hello"));

        l.linkLast("world");
        assertEquals(1, l.indexOf("world"));

        l.linkLast("Hello");
        assertEquals(0, l.indexOf("Hello"));

        assertEquals(-1, l.indexOf("kaposzta"));
    }

    @Test
    void lastIndexOfTest() {
        DoublyLinkedList<String> l = new DoublyLinkedList<>();
        assertEquals(-1, l.lastIndexOf(null));
        assertEquals(-1, l.lastIndexOf("Hello"));

        l.linkLast("Hello");
        assertEquals(0, l.lastIndexOf("Hello"));

        l.linkLast("world");
        assertEquals(1, l.lastIndexOf("world"));

        l.linkLast("Hello");
        assertEquals(2, l.lastIndexOf("Hello"));

        assertEquals(-1, l.lastIndexOf("kaposzta"));
    }

    @Test
    void containsTest() {
        DoublyLinkedList<String> l = new DoublyLinkedList<>();
        assertFalse(l.contains(null));
        assertFalse(l.contains("kaposzta"));

        l.linkFirst("Hello");
        assertTrue(l.contains("Hello"));
        assertFalse(l.contains("kaposzta"));

        l.linkLast("world");
        assertTrue(l.contains("Hello"));
        assertTrue(l.contains("world"));
        assertFalse(l.contains("kaposzta"));
    }

    @Test
    void unlinkFirst() {
        DoublyLinkedList<String> l = new DoublyLinkedList<>();
        assertNull(l.unlinkFirst());
        assertEquals(0, l.size());

        l.linkFirst("Hello");
        l.linkLast("world");
        assertEquals("Hello", l.unlinkFirst());

        assertEquals(1, l.size());
        assertEquals("world", l.first());
        assertEquals("world", l.last());

        assertEquals("world", l.unlinkFirst());
        assertEquals(0, l.size());
    }


    @Test
    void unlinkLast() {
        DoublyLinkedList<String> l = new DoublyLinkedList<>();
        assertNull(l.unlinkLast());
        assertEquals(0, l.size());

        l.linkFirst("Hello");
        l.linkLast("world");
        assertEquals("world", l.unlinkLast());
        assertEquals(1, l.size());
        assertEquals("Hello", l.first());
        assertEquals("Hello", l.last());


        assertEquals("Hello", l.unlinkLast());
        assertEquals(0, l.size());
        assertThrows(NoSuchElementException.class, l::last);
        assertThrows(NoSuchElementException.class, l::first);
    }

    @SuppressWarnings("unchecked")
    @Test
    void cloneTest() {
        DoublyLinkedList<String> l = new DoublyLinkedList<>();
        l.linkLast("1");
        l.linkLast("2");
        l.linkLast("3");

        var copy = (DoublyLinkedList<String>) l.clone();
        assertNotSame(l, copy);
        assertEquals(l, copy);
    }

    @Test
    void equalsTest() {
        DoublyLinkedList<String> l = new DoublyLinkedList<>();
        DoublyLinkedList<String> l2 = new DoublyLinkedList<>();
        assertEquals(l, l2);

        l.linkLast("1");
        l.linkLast("2");
        l.linkLast("3");

        l2.linkLast("1");
        l2.linkLast("2");
        l2.linkLast("3");

        assertEquals(l, l2);

        l2.linkLast("4");
        assertNotEquals(l, l2);
    }

    @Test
    void nodeEqualsTest() {
        DoublyLinkedList.Node<String> n1 = new DoublyLinkedList.Node<>(null, "hello", null);
        DoublyLinkedList.Node<String> n2 = new DoublyLinkedList.Node<>(null, "hello", null);
        assertEquals(n1, n2);

        n2 = new DoublyLinkedList.Node<>(null, "world", null);
        assertNotEquals(n1, n2);
    }

    @Test
    void linkAtTest() {
        DoublyLinkedList<String> l = new DoublyLinkedList<>();
        l.linkLast("1");
        l.linkLast("5");
        l.linkLast("2");
        l.linkLast("3");

        l.linkAt(3, "4");

        assertEquals(5, l.size());
        assertEquals("1,5,2,4,3", l.toString());

        l = new DoublyLinkedList<>();
        l.linkLast("1");
        l.linkLast("3");
        l.linkAt(1, "2");
        assertEquals(3, l.size());
        assertEquals("1,2,3", l.toString());

        l = new DoublyLinkedList<>();
        l.linkAt(0, "Hello");
        assertEquals("Hello", l.first());
        assertEquals("Hello", l.last());
        assertEquals(1, l.size());

    }
}
