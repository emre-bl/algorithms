import java.util.Random;

public class Inversion {

    public static long countingInversions2(int[] arr) {
        int len = arr.length;
        long count = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[i] > arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }
    
    
    public static long countingInversions(int[] arr) {
        return mergeSort(arr, 0, arr.length - 1);
    }
    
    private static long mergeSort(int[] arr, int left, int right) {
        if (arr == null || arr.length <= 1) return 0;
        long inversions = 0;
        if (left < right) { // base case
            int mid = (left + right) / 2;
            inversions += mergeSort(arr, left, mid);
            inversions += mergeSort(arr, mid + 1, right);
            inversions += merge(arr, left, mid, right);
        }
        return inversions;
    }

    private static long merge(int[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];
        for (int i = 0; i < n1; i++) {
            L[i] = arr[p + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[q + j + 1];
        }

        L[n1] = R[n2] = Integer.MAX_VALUE; 
        int i = 0, j = 0;
        
        long inversions = 0;
        for (int k = p; k <= r; k++) {
            if (L[i] <= R[j]) { // burda inversion yok
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
                inversions += n1 - i; 
            }
        }
        return inversions;
    }

    public static int[] generateRandomIntArray(int length, int min, int max) {
        int[] randomArray = new int[length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            randomArray[i] = random.nextInt(max - min + 1) + min;
        }

        return randomArray;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomIntArray(10000, 1, 1000000);

        int[] arr2 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = arr[i];
        }
        
        long inversions1 = countingInversions(arr);
        System.out.println("Number of inversions using countingInversions: " + inversions1);
        long inversions2 = countingInversions2(arr2);
        System.out.println("Number of inversions using countingInversions: " + inversions2);    }
}
