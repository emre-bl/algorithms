import java.util.Arrays;

class Q1{
    public int match(int M[], int W[], int k) {
        Heapsort obj = new Heapsort();
        obj.sort(M);
        obj.sort(W);

        int matchCount = 0;
        int w = 0; 
        for (int i = 0; i < M.length; i++) { // her erkek için uygun kadın var mı yok mu kontrol ediyoruz
            if (w == W.length) { // eğer kadınlar bittiyse eşleşme yok
                break;
            }
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
        int M[] = {11, 12, 13, 14, 15, 16, 17, 18};
        int W[] = {1, 2, 3, 4, 5, 6, 7, 8,9};
        int k = 5;

        Q1 obj = new Q1();
        System.out.println(obj.match(M, W, k));
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