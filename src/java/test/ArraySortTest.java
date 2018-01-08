import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by corning on 2017/12/19.
 */
public class ArraySortTest {

    private int[] array;
    private int[] sortedArray;

    // 计数排序等不支持负数排序
    private int[] positiveArray;
    private int[] positiveArraySorted;

    @Before
    public void setUp() throws Exception {
        // 生成随机数组
        array = randomArray(-1000, 1000, 100);
        // 使用 Arrays.sort() 排序作为对比
        sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray);

        positiveArray = randomArray(0, 1000, 100);
        positiveArraySorted = Arrays.copyOf(positiveArray, positiveArray.length);
        Arrays.sort(positiveArraySorted);
    }

    /**
     * 随机指定范围内N个不重复的数
     * 在初始化的无重复待选数组中随机产生一个数放入结果中，
     * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换
     * 然后从len-2里随机产生下一个随机数，如此类推
     *
     * @param max 指定范围最大值
     * @param min 指定范围最小值
     * @param n   随机数个数
     * @return int[] 随机数结果集
     */
    public int[] randomArray(int min, int max, int n) {
        int len = max - min + 1;

        if (max < min || n > len) {
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min + len; i++) {
            source[i - min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }

    @After
    public void tearDown() throws Exception {
        array = null;
        sortedArray = null;
    }

    @Test
    public void bubbleSort() throws Exception {
        assertArrayEquals(sortedArray, new BubbleSort().sort(array));
    }

    @Test
    public void choiceSort() throws Exception {
        assertArrayEquals(sortedArray, new SelectionSort().sort(array));
    }

    @Test
    public void insertSort() throws Exception {
        assertArrayEquals(sortedArray, new InsertSort().sort(array));
    }

    @Test
    public void shellSort() throws Exception {
        assertArrayEquals(sortedArray, new ShellSort().sort(array));
    }

    @Test
    public void mergeSort() throws Exception {
        assertArrayEquals(sortedArray, new MergeSort().sort(array));
    }

    @Test
    public void mergeSort_merge() throws Exception {
        assertArrayEquals(new int[]{1, 2}, new MergeSort().merge(new int[]{1, 2}, new int[]{}));
        assertArrayEquals(new int[]{1, 2}, new MergeSort().merge(new int[]{1}, new int[]{2}));
        assertArrayEquals(new int[]{1, 2, 3}, new MergeSort().merge(new int[]{1, 3}, new int[]{2}));
    }

    @Test
    public void quickSort() throws Exception {
        assertArrayEquals(sortedArray, new QuickSort().sort(array));
    }

    @Test
    public void heapSort() throws Exception {
        assertArrayEquals(sortedArray, new HeapSort().sort(array));
    }

    @Test
    public void countingSort() throws Exception {
        assertArrayEquals(positiveArraySorted, new CountingSort().sort(positiveArray));
    }

    @Test
    public void bucketSort() throws Exception {
        assertArrayEquals(sortedArray, new BucketSort().sort(array));
    }

    @Test
    public void radixSort() throws Exception {
        assertArrayEquals(sortedArray, new RadixSort().sort(array));
    }

    @Test
    public void radixSort_getNumLenght() throws Exception {
        assertEquals(3, new RadixSort().getNumLenght(-100));
        assertEquals(1, new RadixSort().getNumLenght(1));
    }

}