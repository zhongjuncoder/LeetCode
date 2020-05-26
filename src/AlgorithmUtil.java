public class AlgorithmUtil {

    /**
     * 将数组中两个位置的值交换
     */
    public static void swap(int[] numbers, int positionA, int positionB) {
        int temp = numbers[positionA];
        numbers[positionA] = numbers[positionB];
        numbers[positionB] = temp;
    }

}
