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
        
        public static int[] findNumbers(int n,int k,int l,int[] arr) {
            int kth = quickSelect(arr, 0, arr.length - 1, k-1);
            int[] closestIntegers = new int[l];
            
            int[] diffs = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                diffs[i] = arr[i] - kth;
            }

            for (int i = 0; i < l; i++) {
                int minIdx = getMinIdx(diffs);
                closestIntegers[i] = diffs[minIdx] + kth;
                diffs[minIdx] = Integer.MAX_VALUE;
            }
            
            return closestIntegers;
        }

        private static int getMinIdx(int[] arr) {
            int minDiff = Integer.MAX_VALUE;
            int minDiffIndex = 0;
            for (int i = 0; i < arr.length; i++) {
                if (Math.abs(arr[i]) < minDiff) {
                    minDiffIndex = i;
                    minDiff = Math.abs(arr[i]);
                }
            }
            return minDiffIndex;
        }

        private static int quickSelect(int[] A, int left, int right, int k) {
            if (left == right) {
                return A[left];
            }

            int pivotIndex = partition(A, left, right);
            int kIndex = pivotIndex - left;

            if (k == kIndex) {
                return A[pivotIndex];
            } else if (k < kIndex) {
                return quickSelect(A, left, pivotIndex - 1, k);
            } else {
                return quickSelect(A, pivotIndex + 1, right, k - kIndex - 1);
            }
        }

        private static int partition(int[] A, int left, int right) {
            int pivot = A[right];
            int i = left - 1;

            for (int j = left; j < right; j++) {
                if (A[j] <= pivot) {
                    i++;
                    swap(A, i, j);
                }
            }

            swap(A, i + 1, right);
            return i + 1;
        }

        private static void swap(int[] A, int i, int j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int l = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0 ; i < n ; i++)
                arr[i] = in.nextInt();

            int []result;
            result = findNumbers(n,k,l,arr);
            
            for (int i = 0 ; i < l ; i++) {
                System.out.print(result[i]);
                System.out.print(" ");
            }
            System.out.println();
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