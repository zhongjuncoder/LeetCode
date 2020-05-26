import java.util.Arrays;
import java.util.Stack;

public class SearchAlgorithm {

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(findSumNumbers(new int[]{-5, -1, 0, 5, 9, 11, 13, 15, 22, 35, 46}, 31)));
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(3);
        minStack.push(2);
        minStack.push(-1);
        System.out.println(minStack.getMin());

        minStack.pop();
        System.out.println(minStack.getMin());

        minStack.pop();
        System.out.println(minStack.getMin());
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

    private static int[] findTwoDimensionalArrayValue(int[][] array, int value) {
        int i = 0;
        int j = array[0].length - 1;

        while (j >= 0 && i <= array.length - 1) {
            if (array[i][j] == value) {
                return new int[]{i, j};
            } else if (array[i][j] < value) {
                i++;
            } else {
                j--;
            }
        }
        return new int[]{-1, -1};
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

    /**
     * 找第K大的元素
     */
    private static void findMaxK(int[] numbers, int k) {
        if (k <= 0) {
            System.out.println("没有找到");
            return;
        }
        findMaxK(numbers, k, 0, numbers.length - 1);
    }

    /**
     * 利用快排的思想（从大到小排序），选择一个基准元素，让基准元素左边的都大于等于它，右边的都小于等于它
     * 这样交换后基准元素就是第index+1大的元素。接着判断index+1和k的大小继续查找
     */
    private static void findMaxK(int[] numbers, int k, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            System.out.println("没有找到");
            return;
        }

        int key = numbers[startIndex];
        int start = startIndex;
        int end = endIndex;

        while (start < end) {

            while (start < end && numbers[end] <= key) {
                end--;
            }

            while (start < end && numbers[start] >= key) {
                start++;
            }

            AlgorithmUtil.swap(numbers, start, end);
        }

        AlgorithmUtil.swap(numbers, start, startIndex);

        int index = start + 1;
        if (index == k) {
            System.out.println("第" + k + "大的元素为" + numbers[start]);
        } else if (index < k) {
            findMaxK(numbers, k, start + 1, endIndex);
        } else {
            findMaxK(numbers, k, startIndex, start - 1);
        }

    }

    private static class MinStack {
        Stack<Integer> s1 = new Stack<>();//初始化栈
        Stack<Integer> s2 = new Stack<>();//辅助栈顺序存入最小值

        public MinStack() {
        }

        public void push(int x) {
            s1.push(x);
            if (s2.isEmpty() || s2.peek() >= x) s2.push(x);//比栈顶元素值小或相等就加入辅助栈
        }

        public void pop() {
            int tmp = s1.pop();
            if (tmp == s2.peek()) s2.pop();//弹出栈的元素值如果和辅助栈顶元素值相等，也在辅助栈弹出它
        }

        public int top() {
            return s1.peek();//返回栈顶元素
        }

        public int getMin() {
            return s2.peek();//返回辅助栈栈顶元素即是最小值
        }
    }

}
