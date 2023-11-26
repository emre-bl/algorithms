import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.Comparator;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(in, out);
        out.close();
    }
 
    static class TaskA { 
        public int match(int [] M,int[] W,int k) {
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
        
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] M = new int[n];
            int[] W = new int[n];
            for (int i = 0 ; i < n ; i++)
                M[i] = in.nextInt();
            for (int i = 0 ; i < n ; i++)
                W[i] = in.nextInt();                
            int maxMatch = match(M,W,k);
            out.println(maxMatch);            
        }
 
    }
 
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
        public long nextLong() {
            return Long.parseLong(next());
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