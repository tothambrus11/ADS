package sequences;

public interface IQueue<E> {
    /**
     * @return the size of the list.
     */
    int size();

    /**
     * @return true if the list does not contain any elements (when size = 0)
     */
    boolean isEmpty();

    /**
     * Adds an element to the end of the list.
     * @param element the element to add.
     */
    void enqueue(E element);

    /**
     * Returns the i-th element in the array. It throws an IndexOutOfBoundsException when the
     * provided index is not in the range.
     * @param i index of the element to return
     */
    E get(int i);

    /**
     * @return the first element in the list or throws a NoSuchElementException when
     * the list is empty.
     */
    E first();

    /**
     * Removes an element from the beginning of the list and returns it
     * @return the removed element of the list from the first position.
     */
    E dequeue();
}
