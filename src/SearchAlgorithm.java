import java.util.Arrays;

public class SearchAlgorithm {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findSumNumbers(new int[]{-5, -1, 0, 5, 9, 11, 13, 15, 22, 35, 46}, 31)));
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

    /**
     * 给定一个按升序排列好的数组，求出数组中相加等于sum的两个数
     *
     * @param array {-5, -1, 0, 5, 9, 11, 13, 15, 22, 35, 46}
     * @param sum   31
     * @return {9,22}
     */
    private static int[] findSumNumbers(int[] array, int sum) {
        int i = 0, j = array.length - 1;
        while (i <= j) {
            if (array[i] + array[j] == sum) {
                return new int[]{array[i], array[j]};
            } else if (array[i] + array[j] < sum) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

}
