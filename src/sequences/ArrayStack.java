package sequences;

import java.lang.reflect.Array;

public class ArrayStack<E> implements IStack<E> {
    private final E[] array;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity, Class<E> eClass) {
        this.array = (E[]) Array.newInstance(eClass, capacity);
    }

    @Override
    public E pop() {
        if (size == 0) return null;
        size--;
        var el = array[size];
        array[size] = null;
        return el;
    }

    @Override
    public E top() {
        return array[size - 1];
    }

    @Override
    public void push(E element) {
        if (size == array.length) throw new StackOverflowError();
        array[size] = element;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
