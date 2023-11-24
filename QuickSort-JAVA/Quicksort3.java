import java.util.Arrays;

public class Quicksort3 {

    public static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int[] pivots = findMedianOfThree(arr, low, high);
            int pivot1 = pivots[0];
            int pivot2 = pivots[1];
            int pivot3 = pivots[2];

            int i = low - 1;
            int j = low;
            int k = high + 1;

            while (j < k) {
                if (arr[j] < pivot1) {
                    swap(arr, ++i, j++);
                } else if (arr[j] < pivot2) {
                    swap(arr, j++, low++);
                } else if (arr[j] < pivot3) {
                    ++k;
                } else {
                    swap(arr, --k, j);
                }
            }

            sort(arr, low, i);
            sort(arr, j, k - 1);
            sort(arr, k, high);
        }
    }

    private static int[] findMedianOfThree(int[] arr, int low, int high) {
        int[] pivots = new int[3];
        pivots[0] = arr[low];
        pivots[1] = arr[(low + high) / 2];
        pivots[2] = arr[high];

        Arrays.sort(pivots);

        return pivots;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr  = {5, 4, 3, 2, 1, 0, 9, 8, 7, 6, 10 , 11, 12, 13, 14, 15, 16, 17, 18, 19};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}