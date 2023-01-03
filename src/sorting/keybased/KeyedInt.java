package sorting.keybased;

public class KeyedInt implements KeyedEntry<Integer> {
    private final int key;
    private int value;

    public KeyedInt(int key) {
        this.key = key;
        this.value = key;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public Integer getElement() {
        return value;
    }

    @Override
    public void setElement(Integer element) {
        this.value = element;
    }

    @Override
    public String toString() {
        return "" + key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyedInt keyedInt = (KeyedInt) o;

        if (key != keyedInt.key) return false;
        return value == keyedInt.value;
    }

    @Override
    public int hashCode() {
        int result = key;
        result = 31 * result + value;
        return result;
    }
}
