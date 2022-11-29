package sequences;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {
    @Test
    void testLast() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        assertThrows(NoSuchElementException.class, list::last);

        list.linkFirst("1");
        assertEquals("1", list.last());

        list.linkFirst("2");
        assertEquals("1", list.last());
    }

    @Test
    void testFirst(){
        assertThrows(NoSuchElementException.class, () -> {
            SinglyLinkedList<String> list1 = new SinglyLinkedList<>();
            String s = list1.first();
        });

        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.linkFirst("1");

        assertEquals("1", list.first());

        list.linkFirst("2");
        assertEquals("2", list.first());
    }

    @Test
    void testUnlinkLast(){
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        assertThrows(NoSuchElementException.class, list::unlinkLast);

        list.linkFirst("1");
        assertEquals("1", list.unlinkLast());
        assertTrue(list.isEmpty());

        list.linkFirst("1");
        list.linkFirst("2");
        list.linkFirst("3");

        assertEquals("1", list.unlinkLast());
        assertEquals(2, list.size());
        assertEquals("2", list.unlinkLast());
        assertEquals(1, list.size());
        assertEquals("3", list.unlinkLast());
        assertEquals(0, list.size());
    }

    @Test
    void unlinkFirstTest(){
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        assertThrows(NoSuchElementException.class, list::unlinkFirst);

        list.linkFirst("a");
        assertEquals("a", list.unlinkFirst());
        assertTrue(list::isEmpty);

        list.linkFirst("a");
        list.linkFirst("b");

        assertEquals("b", list.unlinkFirst());
        assertEquals(1, list.size());
        assertEquals("a", list.unlinkFirst());
        assertEquals(0, list.size());
    }

    @Test
    void testLinkLast(){
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.linkLast("Hello");
        assertEquals(1, list.size());
        assertEquals("Hello", list.first());
        assertEquals("Hello", list.last());

        list.linkLast("world");
        assertEquals(2, list.size());
        assertEquals("Hello", list.first());
        assertEquals("world", list.last());
    }

    @Test
    void testLinkAt(){
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.linkLast("1");
        list.linkLast("2");
        list.linkLast("3");
        list.linkLast("4");

        list.linkAt(2, "2.5");

        assertEquals("2.5", list.get(2));
        assertEquals("3", list.get(3));

        list.linkAt(4, "44");
        assertEquals("44", list.get(4));

    }

    @Test
    void linkAt_Start(){
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.linkFirst("1");
        list.linkAt(0,"2");
        assertEquals("2", list.first());
    }

    @Test
    void linkAt_End(){
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.linkFirst("1");
        list.linkLast("2");
        list.linkAt(2, "3");
        assertEquals("3", list.get(2));
    }

    @Test
    void linkAt_OutOfBounds(){
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.linkFirst("1");
        assertThrows(IndexOutOfBoundsException.class, ()->list.linkAt(-1, "kaposzta"));
        assertThrows(IndexOutOfBoundsException.class, ()->list.linkAt(2, "kaposzta"));
    }

    @Test
    void testGet(){
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(0));
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(1));

        list.linkFirst("1");
        assertEquals("1", list.get(0));

        list.linkFirst("2");
        assertEquals("2", list.get(0));
        assertEquals("1", list.get(1));
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(2));


    }
}