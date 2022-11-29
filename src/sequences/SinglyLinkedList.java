package sequences;

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements IDequeue<E> {

    private Node<E> first;
    private int size;

    @Override
    public E last() {
        if (isEmpty()) throw new NoSuchElementException();

        var node = first;
        while (node.next != null) {
            node = node.next;
        }
        return node.element;
    }

    @Override
    public void linkFirst(E element) {
        first = new Node<>(first, element);
        size++;
    }

    @Override
    public void linkLast(E element) {
        Node<E> newNode = new Node<>(null, element);
        if (isEmpty()) {
            first = newNode;
        } else {
            var lastNode = first;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
        }
        size++;
    }

    @Override
    public void linkAt(int i, E element) {
        if (i < 0 || i > size) throw new IndexOutOfBoundsException();
        if (i == 0) {
            linkFirst(element);
            return;
        }
        if (i == size) {
            linkLast(element);
            return;
        }

        var lastNodeBefore = first;
        for (int j = 1; j < i; j++) {
            lastNodeBefore = lastNodeBefore.next;
        }
        var next = lastNodeBefore.next;
        lastNodeBefore.next = new Node<>(next, element);
        size++;
    }

    @Override
    public E unlinkLast() {
        if (isEmpty()) throw new NoSuchElementException();
        if (size == 1) {
            var element = first.element;
            first = null;
            size = 0;
            return element;
        }

        var prev = first;
        var last = first.next;
        while (last.next != null) {
            last = last.next;
            prev = prev.next;
        }

        prev.next = null;
        size--;
        return last.element;
    }

    @Override
    public E unlinkFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        var element = first.element;
        first = first.next;
        size--;
        return element;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    @Override
    public int lastIndexOf(E element) {
        return 0;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

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
        linkFirst(element);
    }

    @Override
    public E get(int i) {
        if(i < 0 || i >= size) throw new IndexOutOfBoundsException();
        Node<E> node = first;
        for (int j = 0; j < i; j++) {
            node = node.next;
        }
        return node.element;
    }

    @Override
    public E first() {
        if (isEmpty()) throw new NoSuchElementException();
        return first.element;
    }

    @Override
    public E dequeue() {
        return unlinkLast();
    }

    private static final class Node<E> {
        private Node<E> next;
        private final E element;

        public Node(Node<E> next, E element) {
            this.next = next;
            this.element = element;
        }
    }
}
