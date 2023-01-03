package sorting;

import java.util.ArrayList;
import java.util.List;

public class QuickSelect<V extends Comparable<V>> {
    /**
     * Returns the kth smallest element from the list
     * @param list
     * @param k
     */
    public V select(List<V> list, int k){
        if(list == null || k <= 0 || k > list.size()) return null;

        V pivot = list.get((int)(list.size() * Math.random() * 0.999999999));
        var S = list.stream().filter(e->e.compareTo(pivot) < 0).toList();
        var G = list.stream().filter(e->e.compareTo(pivot) > 0).toList();
        var E = (int) list.stream().filter(e->e.compareTo(pivot) == 0).count();

        if(k <= S.size()){
            return select(S, k);
        } else if (k <= S.size() + E){
            return pivot;
        } else {
            return select(G, k - S.size() - E);
        }
    }
}
