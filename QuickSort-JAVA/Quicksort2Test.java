import java.util.Arrays;
import java.util.Random;

public class Quicksort2Test {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] sizes = {10000, 10000, 10000, 10000, 1000}; // test with 5 arrays of size 1000
        for (int size : sizes) {
            double[] arr = new double[size];
            for (int i = 0; i < size; i++) {
                arr[i] = rand.nextDouble() * 10000; // fill array with random doubles between 0 and 1000
            }
            double[] sortedArr = arr.clone();
            Arrays.sort(sortedArr); // sort a copy of the array using Arrays.sort
            Quicksort2.quickSort2(arr); // sort the original array using quickSort2
            if (!Arrays.equals(arr, sortedArr)) { // check if the two arrays are equal
                System.out.println("Error: quickSort2 failed to sort array of size " + size);
                return;
            }
        }
        System.out.println("All tests passed!");
    }
}