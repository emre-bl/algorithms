import java.util.Random;

public class CleverSort {
    public static double[] cleverSort(double[] arr) {
        final int INSERTION_SORT_THRESHOLD = 17;
        quicksort(arr, 0, arr.length - 1, INSERTION_SORT_THRESHOLD);
        return arr;
    }

    private static void quicksort(double[] arr, int left, int right, int insertionSortThreshold) {
        if (left < right) {
            if (right - left <= insertionSortThreshold) { // insertion sort
                insertionSort(arr, left, right);
            } else { // quicksort
                int pivotIndex = partition(arr, left, right);
                quicksort(arr, left, pivotIndex - 1, insertionSortThreshold);
                quicksort(arr, pivotIndex + 1, right, insertionSortThreshold);
            }
        }
    }

    private static int partition(double[] arr, int left, int right) { // yukarda kalabalık olmasın diye modüler yaptım
        double pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void insertionSort(double[] arr, int left, int right) { // insertion sort
        for (int i = left + 1; i <= right; i++) {
            double key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(double[] arr, int i, int j) { 
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static double[] generateRandomIntArray(int length, int min, int max) {
        double[] randomArray = new double[length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            randomArray[i] = random.nextInt(max - min + 1) + min;
        }

        return randomArray;
    }


    public static void main(String[] args) {
        double[] arr = generateRandomIntArray(10, 1, 100);
        
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        cleverSort(arr);

        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    
}
