package sequences;

import java.util.LinkedList;

public class LinkedStack <E> implements IStack<E>{
    private final LinkedList<E> list;

    public LinkedStack() {
        this.list = new LinkedList<>();
    }

    @Override
    public E pop() {
        if(list.isEmpty()) return null;
        return list.removeFirst();
    }

    @Override
    public E top() {
        return list.getFirst();
    }

    @Override
    public void push(E element) {
        list.addFirst(element);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
