package sorting.keybased;

public interface KeyedEntry<V>{
    int getKey();

    V getElement();

    void setElement(V element);
}
