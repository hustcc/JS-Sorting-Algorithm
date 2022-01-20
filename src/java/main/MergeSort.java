/**
 * 归并排序
 * @author qrqhuangcy
 * @date 2020/5/4
 */
public class MergeSort implements IArraySort {

    @Override
    public int[] sort(int[] arr) throws Exception {
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (right + left) / 2;

        //分
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        //治
        merge(arr, left, mid, right);
    }


    /**
     * 主要思想， 两个已经排序好的数组， 如何归并
     * 当元素个数趋近单个元素时， 归并即为排序过程
     */
    private void merge(int[] arr, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) { //取等号， 优先匹配左边数组予以填充
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }

        //队尾的直接填充
        while (i <= mid) tmp[k++] = arr[i++];
        while (j <= right) tmp[k++] = arr[j++];

        //覆盖原数组
        System.arraycopy(tmp, 0, arr, left, tmp.length);
    }
}
