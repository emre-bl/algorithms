import java.util.Arrays;

class Question1 {
    public int match(int M[], int W[], int k) {
        QuickSort obj = new QuickSort();
        obj.sort(M, 0, M.length);
        obj.sort(W, 0, W.length);

        int matchCount = 0;
        int w = 0; 
        for (int i = 0; i < M.length; i++) { // her erkek için uygun kadın var mı yok mu kontrol ediyoruz
            if (Math.abs(M[i] - W[w]) <= k) { // eğer varsa eşleştiriyoruz
                matchCount++;
                w++;
            } else { // eğer yoksa 
                if (M[i] > W[w]) { // kadın erkekten çok küçük olduğu için eşleşme olmadıysa erkeği bir sonraki kadınla eşleştirmeyi deniyoruz
                    w++;
                    i--;
                } else { // kadın erkekten çok büyük olduğu için eşleşme olmadıysa kadını bir sonraki erkekle eşleştirmeyi deniyoruz
                    continue; 
                }
            }
        }
        return matchCount;
    }

    public static void main(String[] args) {

        int[] arr = { 5, 4, 3, 2, 1, 0, 9, 8, 7, 6, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };

        Heapsort ob = new Heapsort();
        ob.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}

class QuickSort {
    int partition(int arr[], int s, int e) {
        int pi = arr[e];
        int i = (s - 1);
        for (int j = s; j < e; j++) {
            if (arr[j] <= pi) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int t = arr[i + 1];
        arr[i + 1] = arr[e];
        arr[e] = t;

        return i + 1;
    }

    void sort(int arr[], int s, int e) {
        if (s < e) {
            int pi = partition(arr, s, e);
            sort(arr, s, pi - 1);
            sort(arr, pi + 1, e);
        }
    }
}

class Heapsort {
    void sort(int arr[]) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int t = arr[0];
            arr[0] = arr[i];
            arr[i] = t;

            heapify(arr, i, 0);
        }
    }

    void heapify(int arr[], int n, int i) {
        int largest = i;
        int l = 2 * i + 1; 
        int r = 2 * i + 2; 

        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        if (largest != i) {
            int t = arr[i];
            arr[i] = arr[largest];
            arr[largest] = t;

            heapify(arr, n, largest);
        }
    }
}

