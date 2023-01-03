package sorting;

public abstract class SortUtils {

    public static <V> void swap(V[] array, int i, int j){
        V temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
