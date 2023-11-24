import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Q3 {
    public static void bucketSort(double[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return;
        }

        ArrayList<Double>[] buckets = new ArrayList[12];

        for (int i = 0; i < 12; i++) {
            buckets[i] = new ArrayList<Double>();
        }

        for (int i = 0; i < n; i++) {
            int bucketIndex = 0;
            if (arr[i] > 0 && arr[i] < 0.4) {
                bucketIndex = 0;
            } else if (arr[i] >= 0.4 && arr[i] < 0.6) {
                bucketIndex = 1;
            } else if (arr[i] < 0.7) {
                bucketIndex = 2;
            } else if (arr[i] < 0.75) {
                bucketIndex = 3;
            } else if (arr[i] < 0.8) {
                bucketIndex = 4;
            } else if (arr[i] < 0.83) {
                bucketIndex = 5;
            } else if (arr[i] < 0.86) {
                bucketIndex = 6;
            } else if (arr[i] < 0.89) {
                bucketIndex = 7;
            } else if (arr[i] < 0.92) {
                bucketIndex = 8;
            } else if (arr[i] < 0.95) {
                bucketIndex = 9;
            } else if (arr[i] < 0.97) {
                bucketIndex = 10;
            } else if (arr[i] < 1) {
                bucketIndex = 11;
            }

            buckets[bucketIndex].add(arr[i]);
        }

        for (int i = 0; i < 12; i++) {
            insertionSort(buckets[i]);
        }

        int index = 0;
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
    }

    public static void insertionSort(ArrayList<Double> arr) {
        int n = arr.size();
        for (int i = 1; i < n; ++i) {
            double key = arr.get(i);
            int j = i - 1;
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j = j - 1;
            }
            arr.set(j + 1, key);
        }
    }

    public static void main(String[] args) {
        double[] arr = {0.1, 0.2, 0.4, 0.5, 0.6, 0.7, 0.9, 0.95, 0.97, 0.86, 0.71, 0.85, 0.83, 0.82, 0.81, 0.8, 0.79, 0.78, 0.77, 0.76, 0.75};
        bucketSort(arr);

        System.out.println("Sorted array:");
        for (double num : arr) {
            System.out.print(num + " ");
        }
    }
}


