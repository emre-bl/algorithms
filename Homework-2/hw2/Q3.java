import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Q3 {
    public static void bucketSort(double[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return;
        }

        ArrayList<Double>[] buckets = new ArrayList[16];

        for (int i = 0; i < 16; i++) {
            buckets[i] = new ArrayList<Double>();
        }

        for (int i = 0; i < n; i++) {
            int bucketIndex = 0;
            if (arr[i] < 0.6) {
                bucketIndex = 0;
            } else if (arr[i] < 0.7) {
                bucketIndex = 1;
            } else if (arr[i] < 0.9) { // bura hatalı
                bucketIndex = (int) ((Math.ceil((arr[i] - 0.7) * 100)) / 2 + 1) ;
            } else if (arr[i] < 0.925) {
                bucketIndex = 12; 
            } else if (arr[i] < 0.95) {
                bucketIndex = 13;
            } else if (arr[i] < 0.975) {
                bucketIndex = 14;
            } else {
                bucketIndex = 15;
            }

            buckets[bucketIndex].add(arr[i]);
        }

        for (int i = 0; i < 16; i++) {
            if (buckets[i].size() <= 50) insertionSort(buckets[i]);
            else quickSort(buckets[i], 0, buckets[i].size() - 1);
        }

        int index = 0;
        for(int i = 0; i < 16; i++) {
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

    public static void quickSort(ArrayList<Double> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(ArrayList<Double> arr, int low, int high) {
        double pivot = arr.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr.get(j) < pivot) {
                i++;

                double temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }

        double temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);

        return i + 1;
    }


    public static double[] generateArray(int n) {
        double[] result = new double[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            // Generate a random value between 0 and 1
            double u = random.nextDouble();

            // Calculate the value based on the inverse transform sampling
            double value = Math.pow(u, 0.25); // Inverse of f(x) = 4x^3
            result[i] = value;
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 10000;
        double[] arr = generateArray(n);
        DecimalFormat df = new DecimalFormat("0.00");
        bucketSort(arr);
        /*  
        for (double value : arr) {
            System.out.print(df.format(value) + " ");
        }*/
    }
}


