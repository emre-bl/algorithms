import java.util.Arrays;
import java.util.Arrays;
import java.util.Random;

public class Q5 {
    public static int[] findNumbers(int n,int k,int l,int[] arr) {
        int[] closestIntegers = new int[l];
        int last_idx, first_idx;
        System.out.println("Last idx: " + (k + (int)Math.ceil((double)l/2) -1));
        last_idx = quickSelect(arr, 0, arr.length-1, k + (int)Math.ceil(l/2) -1);
        first_idx = quickSelect(arr, 0, arr.length-1, k - l/2 -1);
        
        System.out.println("first_idx: " + first_idx);
        System.out.println("last_idx: " + last_idx);
        // return the integers between first_idx and last_idx
        int idx = 0;
        for(int i = first_idx; i <= last_idx; i++) {
            closestIntegers[idx++] = arr[i];
        }

        
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(closestIntegers));
        

        return closestIntegers;
    }

    private static int getMaxIndex(int[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static int quickSelect(int[] A, int left, int right, int k) {
        if (left == right) {
            return left;
        }

        int pivotIndex = partition(A, left, right);
        int kIndex = pivotIndex - left;

        if (k == kIndex) {
            return kIndex;
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
        int[] A = {10,3,7,8,5,4,6,9,2,1};
        int k = 7;
        int l = 3;
        System.out.println(Arrays.toString(A));
        int[] closestIntegers = findNumbers(0, k, l, A);
        System.out.println(Arrays.toString(closestIntegers));
    }

    // Helper method to generate a random positive integer array
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++)  arr[i] = Math.abs(random.nextInt() % (size * 10)) + 1;
        return arr;
    }
}
