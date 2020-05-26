import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 有序度是指数组中具有有序关系的元素对的个数，即是a[i]<a[j]&&i<j。在{4,5,6,3,2,1}中有序度为3，有序的元素对为(4,5),(4,6),(5,6)
 * <p>
 * 对于一个完全有序的数组，比如{1,2,3,4,5,6}，有序度就是n*(n-1)/2，也就是15。这种数组被称为满有序度。
 * <p>
 * 逆序度=满有序度-有序度。排序就是一个增加有序度，减少逆序度的过程。
 * 在冒泡排序中，每交换一次有序度就加一，不管算法怎么改进，交换的次数总是确定的(逆序度的大小)
 * </p>
 */
public class SortAlgorithm {

    public static void main(String[] args) {
        int[] number = new int[]{31, 19, 5, 3};
        //insertSort(number);
        System.out.println(Arrays.toString(number));

        int[] number2 = new int[]{1, 1, 2, 0, 9, 3, 12, 7, 2, 3, 4, 21, 22};
        System.out.println(Arrays.toString(number2));
    }

    /**
     * 插入排序
     * <p>
     * 将数组分为已排序和未排序的两部分，每次从未排序中取一个元素,把自己插入到已排序的合适位置（需要原本在它前面的元素往后移动为它留出那个位置）
     * <p>
     * 当数组是已排序的情况下，最好时间复杂度为O(n)
     * <p>
     * 当数组是倒序的情况下，最后时间复杂度为O(n^2)
     * <p>
     * 插入排序是稳定排序
     *
     * @param numbers {4, 5, 6, 3, 2, 1}
     */
    private static void insertSort(int[] numbers) {
        int count = 0;
        for (int i = 1; i < numbers.length; i++) {
            for (int j = i; j > 0; j--) {
                count++;
                if (numbers[j] < numbers[j - 1]) {
                    swap(numbers, j, j - 1);
                } else {
                    break;
                }
            }
        }
        System.out.println(count);
    }

    /**
     * 不是每次都两个数进行交换，而是先把大数都往后移动，再把小的数移动到合适的位置
     */
    private static void insertSortMinSwap(int[] numbers) {
        int count = 0;
        for (int i = 1; i < numbers.length; i++) {
            int temp = numbers[i];
            for (int j = i; j > 0; j--) {
                count++;
                if (temp < numbers[j - 1]) {
                    numbers[j] = numbers[j - 1];
                } else {
                    numbers[j] = temp;
                    break;
                }
            }
        }
        System.out.println(count);
    }

    /**
     * 冒泡排序
     * <p>
     * 依次比较相邻的两个元素，如果满足需求就交换。每遍历一次就能把最大/最小的那个元素放在后面合适的位置
     * <p>
     * 当元素本来就是排好序的情况下只需要遍历一次，最好时间复杂度是O(n)
     * </p>
     * 当元素都是逆序的时候需要比较(n*(n-1))/2次，(n-1)+(n-2)+...+1，所以最差的时间复杂度是O(n^2)
     * <p>
     * 当有相邻的两个元素大小相等的时候，我们不做交换，相同大小的元素在排序后不会改变顺序，所以冒泡排序是稳定的排序算法
     * <p>
     * [4,5,2,1,2,3],排序后[1,2,2,3,4,5]，原本在index==2位置的2是在index==4位置上的2的前面的，排序后还是在它前面就表示是稳定排序
     */
    private static void bubbleSort(int[] numbers) {
        //记录最后一次发生交换的位置，它后面的数据都是有序了的，则不再遍历
        //例如[5,4,3,2,1,0,6,7,8,9],遍历一次后最后一次交换的位置为index==4和index==5，则index==6后都是有序了的，下次只需要遍历index==0到index==4就行
        int endIndex = numbers.length - 1;
        for (int i = 0; i < endIndex; i++) {
            boolean haveSwap = false;           //如果遍历完一遍后没有交换元素，则整个数组已经是有序了的
            int temp = endIndex;
            for (int j = 0; j < temp; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    swap(numbers, j, j + 1);
                    haveSwap = true;
                    endIndex = j;
                }
            }
            if (!haveSwap) {
                //如果某次没有数据交换说明已经排序完成了
                break;
            }
        }
    }

    /**
     * 鸡尾酒排序
     * <p>
     * 在上面的基础上先从左往右排序，再从右往左排序，如此往复。如果数组左边一部分是有序的，则可以减少比较的次数
     * </p>
     */
    private static void bubbleSortCocktail(int[] numbers) {
        int startIndex = 0;
        int endIndex = numbers.length - 1;
        while (true) {
            boolean stop = true;
            int tempStart = startIndex;
            int tempEnd = endIndex;

            for (int i = tempStart; i < tempEnd; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    swap(numbers, i, i + 1);
                    stop = false;
                    endIndex = i;
                }
            }
            if (stop) {
                break;
            }

            stop = true;
            tempStart = startIndex;
            tempEnd = endIndex;
            for (int i = tempEnd; i > tempStart; i--) {
                if (numbers[i] < numbers[i - 1]) {
                    swap(numbers, i, i - 1);
                    startIndex = i - 1;
                    stop = false;
                }
            }
            if (stop) {
                break;
            }
        }
    }

    /**
     * 快速排序
     * <p>
     * 选择一个基准元素，从数组从右往左遍历找到比基准元素小的位置，再从数组从左往右找到比基准元素大的位置，接着交换两个位置的元素。
     * </p>
     * 循环直到两边的位置重合，再把基准元素和重合位置的元素交换，这样在基准元素左边的都是小于它的，右边的都是大于它的。
     * <p>
     * 时间复杂度：T(1)=C（常数）         n=1
     * T(n)=2*T(n/2)  n>1
     * <p>
     * 推导：      T(n)=2*(2*T(n/4))=4*T(n/4)
     * =2*(2*(2*T(n/8)))=8*T(n/8)
     * =2^k * T(n/2^k)
     * 当T(n/2^k)=T(1)时，即n/2^k=1，所以k=log2 n
     * </p>
     */
    private static void quickSort(int[] numbers, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int key = numbers[startIndex];
        int left = startIndex;          //不要加1
        int right = endIndex;
        while (left < right) {
            while (left < right && numbers[right] >= key) { //在右边找到比基准元素小的位置
                right--;
            }

            while (left < right && numbers[left] <= key) {  //在左边找到比基准元素大的位置
                left++;
            }

            if (left < right) {
                swap(numbers, left, right);                   //把比基准元素小的放左边，大的放右边
            }
        }

        swap(numbers, startIndex, left);                    //把基准元素放到分割点
        quickSort(numbers, startIndex, left - 1);
        quickSort(numbers, right + 1, endIndex);
    }

    /**
     * 选择排序
     * <p>
     * 将数组分为已排序和未排序两部分，每次从后面的未排序中选择最小/最大的元素和未排序的第一个交换
     * <p>
     * 因为无论数组原本是有序还是没序，都需要从未排序的部分选择，所以最好和最坏的时间复杂度都是O(n^2)
     * <p>
     * 选择排序不是稳定的算法，{5,8,5,2,9} 这样一组数据，第一次找到2为最小值，然后和第一个5交换，这样两个5的顺序就变了。
     *
     * @param numbers {4, 5, 6, 3, 2, 1}
     */
    private static void selectSort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int index = i;                  //i=0
            for (int j = i; j < numbers.length; j++) {
                if (numbers[index] > numbers[j]) {
                    index = j;              //index最后为5
                }
            }
            if (index != i) {
                swap(numbers, index, i);    //把4和1交换，这样已排序的为{1}，未排序的为{5,6,3,2,4}
            }
        }
    }

    /**
     * 归并排序
     * <p>
     * 利用分治的思想，把一个数组的排序变为两个小数组的排序，然后把小数组的排序结果合并起来，这样最终整个数组就是已排好序的了
     * <p>
     * 无论数组是有序还是无序都需要对数组进行拆分和合并，所以时间复杂度都是O(nlogn)。虽然每次在合并数组的时候都需要额外申请所合并数组的大小的空间，
     * 不过因为每次函数调用完空间都会被释放，所以空间复杂度是O(n)。
     * <p>
     * 归并排序是稳定的排序
     *
     * @param numbers [11,8,3,9,7,1,2,5]
     * @param start   0
     * @param end     7
     */
    private static void mergeSort(int[] numbers, int start, int end) {
        if (start >= end) {
            return;
        }

        int middle = start + (end - start) / 2;
        mergeSort(numbers, start, middle);          //0,3
        mergeSort(numbers, middle + 1, end);  //4,7
        mergeSort(numbers, start, middle, middle + 1, end);
    }

    /**
     * 将归并排序分成的两部分数组排序
     */
    private static void mergeSort(int[] numbers, int firstStart, int firstEnd, int secondStart, int secondEnd) {
        int[] array = new int[secondEnd - firstStart + 1];      //用于存储排序好的数据
        int index = 0;
        int i = firstStart, j = secondStart;
        while (i <= firstEnd && j <= secondEnd) {
            if (numbers[i] < numbers[j]) {
                array[index++] = numbers[i++];
            } else {
                array[index++] = numbers[j++];
            }
        }

        //如果两部分的数组长度不一样，则还会有一个数组有元素剩余，则把剩余的元素放入array
        while (i <= firstEnd) {
            array[index++] = numbers[i++];
        }

        while (j <= secondEnd) {
            array[index++] = numbers[j++];
        }

        //将排序好的数组复制回原数组
        System.arraycopy(array, 0, numbers, firstStart, secondEnd + 1 - firstStart);
    }

    /**
     * 将数组中两个位置的值交换
     */
    public static void swap(int[] numbers, int positionA, int positionB) {
        int temp = numbers[positionA];
        numbers[positionA] = numbers[positionB];
        numbers[positionB] = temp;
    }

}
