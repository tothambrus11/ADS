package practise;

class Solution {

    public static void stableSort(String[][] table, int column) {
        if(table == null) return;

        for(int i = 1; i < table.length; i++){
            for(int j = i; j >= 1; j--){
                if(table[j-1][column].compareTo(table[j][column]) > 0){
                    swap(table, j, j-1);
                } else {
                    break;
                }
            }
        }
    }

    private static void swap(String[][] table, int r1, int r2){
        var t = table[r1];
        table[r1] = table[r2];
        table[r2] = t;
    }
}
