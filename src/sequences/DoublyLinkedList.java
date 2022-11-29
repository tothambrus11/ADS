package sequences;

import java.util.NoSuchElementException;
import java.util.Objects;

public class DoublyLinkedList<E> implements IDequeue<E> {
    public static class Node<E> {
        Node<E> prev;
        Node<E> next;
        E element;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.next = next;
            this.element = element;
        }


        /**
         * @param o other object to compare to
         * @return true if the other object is a node and has the same element as this node
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?> node = (Node<?>) o;

            return Objects.equals(element, node.element);
        }
    }

    private Node<E> first;
    private Node<E> last;

    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E element) {
        linkLast(element);
    }

    @Override
    public E first() {
        if (first == null) throw new NoSuchElementException();
        return first.element;
    }

    @Override
    public E dequeue() {
        return unlinkFirst();
    }

    @Override
    public E last() {
        if (last == null) throw new NoSuchElementException();
        return last.element;
    }

    @Override
    public void linkFirst(E element) {
        Node<E> f = first;
        first = new Node<>(null, element, f);
        if (f != null) {
            f.prev = first;
        } else {
            last = first;
        }
        size++;
    }

    @Override
    public void linkLast(E element) {
        Node<E> l = last;
        last = new Node<>(last, element, null);
        if (l != null) {
            l.next = last;
        } else {
            first = last;
        }
        size++;
    }

    @Override
    public void linkAt(int i, E element) {
        if (size < i) return;
        if (size == i) {
            linkLast(element);
            return;
        }

        Node<E> node = first;
        for (int j = 0; j < i; j++) {
            node = node.next;
        }
        if (node != null) {
            Node<E> newNode = new Node<>(node.prev, element, node);
            node.prev.next = newNode;
            node.prev = newNode;
        }
        size++;
    }

    @Override
    public E unlinkLast() {
        E removed = null;
        if (last != null) {
            removed = last.element;
            Node<E> beforeLast = last.prev;
            last = beforeLast;
            if (beforeLast != null) { // there was a node before the last one
                beforeLast.next = null;
            } else { // we removed the last one
                first = null;
            }
            size--;
        }
        return removed;
    }

    @Override
    public E unlinkFirst() {
        E removed = null;
        if (first != null) {
            removed = first.element;
            final Node<E> second = first.next;
            first = second;
            if (second != null) { // there was a 2nd element
                second.prev = null;
            } else { // this was the only element
                last = null;
            }
            size--;
        }
        return removed;
    }

    @Override
    public E get(int i) {
        if (i >= size) throw new IndexOutOfBoundsException();
        for (Node<E> node = first; node != null; node = node.next) {
            if (i == 0) return node.element;
            i--;
        }
        return null;
    }

    @Override
    public String toString() {
        if (size == 0) return "";
        StringBuilder res = new StringBuilder();
        for (Node<E> node = first; node != null; node = node.next) {
            res.append(node.element);
            if (node != last) res.append(",");
        }
        return res.toString();
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        for (Node<E> node = first; node != null; node = node.next) {
            if (Objects.equals(node.element, element)) return index;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        int index = size - 1;
        for (Node<E> node = last; node != null; node = node.prev) {
            if (Objects.equals(node.element, element)) return index;
            index--;
        }
        return index;
    }

    @Override
    public boolean contains(E element) {
        for (Node<E> node = first; node != null; node = node.next) {
            if (Objects.equals(node.element, element)) return true;
        }
        return false;
    }

    @Override
    public Object clone() {
        DoublyLinkedList<E> copy = new DoublyLinkedList<>();
        for (Node<E> node = first; node != null; node = node.next) {
            copy.linkLast(node.element);
        }
        return copy;
    }


    /**
     * @param o other object to compare to
     * @return true if the other object is a linked list and has the same elements in the same order
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoublyLinkedList<E> that = (DoublyLinkedList<E>) o;

        if (size != that.size) return false;

        for (Node<E> node = first, otherNode = that.first; node != null; node = node.next, otherNode = otherNode.next) {
            if (!Objects.equals(node.element, otherNode.element)) return false;
        }
        return true;
    }

}
