import java.util.Arrays;

public class Quicksort {
    
    public static void quickSort2(double[] arr) {
        quickSort2(arr, 0, arr.length - 1);
    }
    
    private static void quickSort2(double[] arr, int left, int right) {
        if (left < right) {
            if (arr[left] > arr[right]) { // 1. pivot daha küçük olmalı
                swap(arr, left, right);
            }
            double pivot1 = arr[left]; double pivot2 = arr[right];
            int i = left + 1; int k = right - 1;
            int j = i; // j : pivot1 ile pivot2 arasında dolaşacak
            int d = 0; 
            while (j <= k) { 
                if (d >= 0) { 
                    if (arr[j] < pivot1) {
                        swap(arr, i, j);
                        i++;
                        j++;
                        d++;
                    } else if (arr[j] >= pivot2) {
                        while (arr[k] > pivot2 && j < k) {
                            k--;
                        }
                        swap(arr, j, k);
                        k--;
                        d--;
                    } else {
                        j++;
                    }
                } else { //pivot1 işi bitti, pivot2'ye geç
                    if (arr[k] > pivot2) {
                        k--;
                        d--;
                    } else if (arr[k] < pivot1) {
                        swap(arr, i, j);
                        swap(arr, i, k);
                        i++;
                        j++;
                        d++;
                    } else {
                        swap(arr, j, k);
                        j++;
                    }
                }
            }
            i--;
            k++;
            swap(arr, left, i);
            swap(arr, right, k);
            quickSort2(arr, left, i - 1);
            quickSort2(arr, i + 1, k - 1);
            quickSort2(arr, k + 1, right);
        }
    }
    
    private static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


public static void main(String[] args) {
    double[] arr = { 3.0, 1.0, 4.0, 1.0, 5.0, 9.0, 2.0, 6.0, 5.0, 3.0 };
    System.out.println("Before sorting: " + Arrays.toString(arr));
    Quicksort.quickSort2(arr);
    System.out.println("After sorting: " + Arrays.toString(arr));
}

}
