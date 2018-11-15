public class SearchAlgorithm {

    public static void main(String[] args) {

    }

    /**
     * 二分查找
     * <p>
     * 用于在有序的数组中快速的找到数据，时间复杂度为O(logN)
     */
    private int binarySearch(int[] array, int key) {
        int start = 0, end = array.length - 1;
        int middle;
        while (start <= end) {                      //循环终止条件为<=
            middle = start + (end - start) / 2;     //之所以不用(start+end)/2是因为如果两个很大的数相加会导致溢出
            if (array[middle] == key) {
                return middle;
            } else if (array[middle] > key) {
                end = middle - 1;                       //-1
            } else {
                start = middle + 1;                     //+1
            }
        }
        return -1;
    }

}
