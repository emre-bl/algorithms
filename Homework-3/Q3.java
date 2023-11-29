public class Q3 {
    public static void main(String[] args) {
        
    }   


    public static int switchCount(int[] a, int[] b) {
        // Sort the arrays based on the exit times (b)
        quicksort(a, b, 0, a.length - 1);

        int count = 1; // Initialize count to 1 since the first person switches on the lights
        int lastExitTime = b[0]; // Track the exit time of the last person

        for (int i = 1; i < a.length; i++) {
            if (a[i] > lastExitTime) {
                // If the next person enters after the lights are switched off, increment count
                count++;
            }
            lastExitTime = Math.max(lastExitTime, b[i]);
        }

        return count;
    }

    private static void quicksort(int[] a, int[] b, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(a, b, low, high);
            quicksort(a, b, low, pivotIndex - 1);
            quicksort(a, b, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] a, int[] b, int low, int high) {
        int pivot = b[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (b[j] < pivot) {
                i++;
                swap(a, b, i, j);
            }
        }

        swap(a, b, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] a, int[] b, int i, int j) {
        int tempA = a[i];
        int tempB = b[i];
        a[i] = a[j];
        b[i] = b[j];
        a[j] = tempA;
        b[j] = tempB;
    }
}
