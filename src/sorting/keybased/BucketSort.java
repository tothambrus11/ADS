package sorting.keybased;

import java.util.ArrayList;
import java.util.List;

public class BucketSort<E extends KeyedEntry<?>> implements KBSorter<E> {
    @Override
    public void sort(List<E> listToSort) {
        if(listToSort.size() <= 2) return;

        E minElement = listToSort.stream().reduce((a,b)->a.getKey() <= b.getKey() ? a : b).get();
        E maxElement = listToSort.stream().reduce((a,b)->a.getKey() >= b.getKey() ? a : b).get();
        int startKey = minElement.getKey();
        int keyRangeLength = maxElement.getKey() - minElement.getKey() + 1;

        ArrayList<ArrayList<E>> buckets = new ArrayList<>(keyRangeLength);
        for (int i = 0; i < keyRangeLength; i++) {
            buckets.add(new ArrayList<>());
        }
        for(var el : listToSort) buckets.get(el.getKey() - minElement.getKey()).add(el);
        listToSort.clear();
        for (var b : buckets) {
            listToSort.addAll(b);
        }
    }
}
