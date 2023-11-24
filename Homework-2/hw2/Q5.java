import java.util.Arrays;

public class Q5 {
    public static int[] findClosestIntegers(int[] A, int k, int l) {
        int kthOrderStatistic = quickSelect(A, 0, A.length - 1, k);
        int[] closestIntegers = new int[l];
        int max_diff = Integer.MIN_VALUE;
        int diff, temp_diff;

        for(int i = 0; i < l; i++) {
            closestIntegers[i] = A[i];
            diff = Math.abs(A[i] - kthOrderStatistic);
            if(diff > max_diff) {
                max_diff = diff;
            }
        }
        
        for(int i = l; i < A.length; i++) {
            diff = Math.abs(A[i] - kthOrderStatistic);
            if(diff < max_diff) {
                System.out.println(Arrays.toString(closestIntegers));
                for(int j = 0; j < l; j++) {
                    temp_diff = Math.abs(kthOrderStatistic - closestIntegers[j]);
                    if (temp_diff == max_diff) {
                        closestIntegers[j] = A[i];
                        break;
                    }
                }
                System.out.println(Arrays.toString(closestIntegers));
                max_diff = diff;
                for(int j = 0; j < l; j++) {
                    temp_diff = Math.abs(kthOrderStatistic - closestIntegers[j]);
                    if (temp_diff > max_diff) {
                        max_diff = temp_diff;
                    }
                }
                System.out.println("--------------------");
            }
        }
        System.out.println(kthOrderStatistic);
        return closestIntegers;
    }

    private static int quickSelect(int[] A, int left, int right, int k) {
        if (left == right) {
            return A[left];
        }

        int pivotIndex = partition(A, left, right);
        if (k == pivotIndex) {
            return A[k];
        } else if (k < pivotIndex) {
            return quickSelect(A, left, pivotIndex - 1, k);
        } else {
            return quickSelect(A, pivotIndex + 1, right, k);
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
        int[] A = {3, 2, 5, 1, 7, 10, 8, 9, 4, 6,5,6};
        int k = 5;
        int l = 5;

        int[] closestIntegers = findClosestIntegers(A, k, l);
        System.out.println(Arrays.toString(closestIntegers));
    }
}
