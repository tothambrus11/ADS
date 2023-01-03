package sorting.quickselect;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class QuickSelectLazy<V extends Comparable<V>> implements SelectNthElement<V> {
    private static final Random RANDOM = new Random();

    /**
     * Returns the kth smallest element from the list
     * @param array
     * @param k
     */
    @SuppressWarnings("unchecked")
    public V select(V[] array, int k){
        if(k <= 0 || k > array.length) throw new NoSuchElementException();

        V pivot = array[RANDOM.nextInt(array.length)];
        var S = (V[]) Arrays.stream(array).filter(e->e.compareTo(pivot) < 0).toArray(Comparable[]::new);
        var G = (V[]) Arrays.stream(array).filter(e->e.compareTo(pivot) > 0).toArray(Comparable[]::new);
        var E = (int) Arrays.stream(array).filter(e->e.compareTo(pivot) == 0).count();

        if(k <= S.length){
            return select(S, k);
        } else if (k <= S.length + E){
            return pivot;
        } else {
            return select(G, k - S.length - E);
        }
    }
}
