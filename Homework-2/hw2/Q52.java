import java.util.Arrays;
import java.util.Random;


public class Q52 {

    public static int[] findNumbers(int n,int k,int l,int[] arr) {
        int kth = quickSelect(arr, 0, arr.length - 1, k-1);
        int[] closestIntegers = new int[l];
        
        int[] diffs_abs = new int[arr.length];
        int[] diffs = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            diffs_abs[i] = Math.abs(arr[i] - kth);
            diffs[i] = arr[i] - kth;
        }

        int max_diff = quickSelect(diffs_abs, 0, diffs_abs.length-1, l-1);

        System.out.println("kth: " + kth);
        System.out.println("max_diff: " + max_diff);

        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Math.abs(diffs[i]) <= max_diff) {
                closestIntegers[idx++] = arr[i];
            }
            if (idx == l) {
                break;
            }
        }
        
        return closestIntegers;
    }

    private static int quickSelect(int[] A, int left, int right, int k) {
        if (left == right) {
            return A[left];
        }

        int pivotIndex = partition(A, left, right);
        int kIndex = pivotIndex - left;

        if (k == kIndex) {
            return A[pivotIndex];
        } else if (k < kIndex) {
            return quickSelect(A, left, pivotIndex - 1, k);
        } else {
            return quickSelect(A, pivotIndex + 1, right, k - kIndex - 1);
        }
    }

    private static int partition(int[] A, int left, int right) {
        int pivot = A[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (A[j] <= pivot) {
                i++;
                swap(A, i, j);
            }
        }

        swap(A, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void main(String[] args) {
        int[] A = generateRandomArray(10);
        int k = 1;
        int l = 4;
        System.out.println(Arrays.toString(A));
        int[] closestIntegers = findNumbers(0, k, l, A);
        System.out.println(Arrays.toString(closestIntegers));
    }

    // Helper method to generate a random integer array
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = Math.abs(random.nextInt() % size + 1);
        }

        return arr;
    }
}
