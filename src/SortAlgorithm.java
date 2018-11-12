import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;

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

    }

    /**
     * 冒泡排序
     * <p>
     * 依次比较相邻的两个元素，如果满足需求就交换。每遍历一次就能把最大/最小的那个元素放在后面合适的位置
     * <p>
     * 当元素本来就是排好序的情况下只需要遍历一次，最好时间复杂度是O(1)
     * </p>
     * 当元素都是逆序的时候需要比较(n*(n-1))/2次，n*(n-1)*(n-2)*...*1，所以最差的时间复杂度是O(n^2)
     * <p>
     * 当有相邻的两个元素大小相等的时候，我们不做交换，相同大小的元素在排序后不会改变顺序，所以冒泡排序是稳定的排序算法
     * <p>
     * [4,5,2,1,2,3],排序后[1,2,2,3,4,5]，原本在2位置的2是在4位置上的2的前面的，排序后还是在他前面就表示是稳定排序
     */
    private static void bubbleSort(int[] numbers) {
        for (int i = 0, length = numbers.length; i < length; i++) {
            boolean haveSwap = false;
            for (int j = 0; j < length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    swap(numbers, j, j + 1);
                    haveSwap = true;
                }
            }
            if (!haveSwap) {
                //如果某次没有数据交换说明已经排序完成了
                break;
            }
        }
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
        for (int i = 1; i < numbers.length; i++) {
            int j = i - 1;          //i==3,j==2的时候
            for (; j >= 0; j--) {
                if (numbers[j] > numbers[i]) {
                    numbers[j + 1] = numbers[j]; //依次把6,5,4往后移动
                } else {
                    break;
                }
            }
            if (j + 1 != i) {
                numbers[j + 1] = numbers[i];      //再把3放到0的位置上
            }
        }
        //这样需要移动3次，和3元素放到合适的位置，其实只进行了4次赋值操作。
        //而如果是进行冒泡排序，则需要进行3次交换操作，即进行了3*3次赋值操作，所以插入排序是优于冒泡排序的
    }

    /**
     * 选择排序
     * <p>
     * 和插入排序一样，都是将元素分为已排序和未排序的两部分。不过它是每次从未排序的那部分中选择满足需求的元素，然后放在已排序那里
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
     * 将数组中两个位置的值交换
     */
    private static void swap(int[] numbers, int positionA, int positionB) {
        int temp = numbers[positionA];
        numbers[positionA] = numbers[positionB];
        numbers[positionB] = temp;
    }

}
