package sequences;

public interface IStack<E>{
    E pop();
    E top();
    void push(E element);
    int size();
    boolean isEmpty();
}
