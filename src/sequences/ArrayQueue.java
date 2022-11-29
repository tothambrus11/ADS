package sequences;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements IQueue<E> {
    private int size = 0;
    private int startIndex = 0;
    private E[] array;

    private final Class<E> eClass;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity, Class<E> eClass) {
        array = (E[]) Array.newInstance(eClass, capacity);
        this.eClass = eClass;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void enqueue(E element) {
        if (array.length == size) { // we need to extend the array
            var newArray = (E[])Array.newInstance(eClass, size * 2);
            var firstPartLength = size - startIndex;
            System.arraycopy(array, startIndex, newArray, 0, firstPartLength);

            if(startIndex != 0) {
                System.arraycopy(array, 0, newArray, firstPartLength, startIndex + 1);
            }

            array = newArray;
        }
        array[(startIndex + size) % array.length] = element;
        size++;
    }

    @Override
    public E get(int i) {
        if (i >= size) throw new IndexOutOfBoundsException();
        return array[(startIndex + i) % size];
    }

    @Override
    public E first() {
        if (isEmpty()) throw new NoSuchElementException();
        return array[startIndex];
    }

    @Override
    public E dequeue() {
        E removed = array[startIndex];
        array[startIndex] = null;
        startIndex = (startIndex + 1) % array.length;
        size--;
        return removed;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(get(i));
            if(i != size-1) builder.append(",");
        }
        return builder.toString();
    }
}
