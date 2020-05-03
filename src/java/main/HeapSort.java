import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort implements IArraySort {

    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int len = arr.length;

        buildMaxHeap(arr, len);

        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }

    private void buildMaxHeap(int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }
    }

    /**
     * 递归方式堆化
     */
    private void heapifyRecursive(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapifyRecursive(arr, largest, len);
        }
    }

    /**
     * 非递归方式堆化
     */
    private void heapify(int[] arr, int i, int len) {
        int tmp = arr[i];

        for (int j = i * 2 + 1; j < len; j = j * 2 + 1) {
            //比较左右子结点谁更大
            if (j + 1 < len && arr[j] < arr[j + 1]) {
                j++;
            }
            //如果子节点没父节点大，跳过
            if (arr[j] <= tmp) {
                break;
            } else {
                //将子节点的值赋给父节点
                arr[i] = arr[j];
                //升级为新的父节点
                i = j;
            }
        }

        arr[i] = tmp;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
