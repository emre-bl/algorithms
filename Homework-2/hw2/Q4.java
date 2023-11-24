class Q4 {
    public static int findKthOrderStatistic(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        
        while (true) {
            int pivotIndex = partition(arr, left, right);
            
            if (pivotIndex == k - 1) {
                return arr[pivotIndex];
            } else if (pivotIndex < k - 1) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
    }
    
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, right);
        return i + 1;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    int[] closestIntegers(int[] arr, int l){
        if(l % 2 == 0) {
            for(int i = l/2 + 1; )
        }
    }





}
    
