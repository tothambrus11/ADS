package sorting;

import java.util.ArrayList;

public class QuickSort<E extends Comparable<E>> implements Sorter<E>{
    @Override
    public void sort(E[] array) {
        if(array.length == 0) return;
        sort(array, 0, array.length-1);
    }

    public static void main(String[] args) {
        var a = new QuickSort<Integer>();

        var b = new Integer[]{1,3,5,9,2,42,32,4};
        a.sort(b);
    }

    private void sort(E[] array, int start, int end){
        if(end <= start){
            return;
        }
        E pivotValue = array[start + (end - start)/2];

        int p1 = start;
        int p2 = end;

        while(p1 <= p2){
            while(array[p1].compareTo(pivotValue) < 0) p1++;
            while(array[p2].compareTo(pivotValue) > 0) p2--;
            if(p1 > p2) break;

            swap(array, p1, p2);
            p1++;
            p2--;
        }

        sort(array, start, p2);
        sort(array, p1, end);
    }

    private void swap(E[] array, int i, int j){
        E elem = array[i];
        array[i] = array[j];
        array[j] = elem;
    }
}

// {1} 2 3 4 8 5
//