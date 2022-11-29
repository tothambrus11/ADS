package sequences;

public interface IDequeue<E> extends IQueue<E>{
    /**
     * Returns the last element, or throws a NoSuchElementException when the list is empty.
     */
    E last();

    void linkFirst(E element);

    void linkLast(E element);

    void linkAt(int i, E element);

    E unlinkLast();

    E unlinkFirst();

    int indexOf(E element);

    int lastIndexOf(E element);

    boolean contains(E element);

}
