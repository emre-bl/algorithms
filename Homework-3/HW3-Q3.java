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
import java.util.*;

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

	    public int switchCount(int [] a, int [] b)  {
            quicksort(b, a, 0, a.length - 1);

            int count = 1; 
            int lastExitTime = b[0]; 

            for (int i = 1; i < a.length; i++) {
                if (a[i] > lastExitTime) {
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

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int a[] = new int[n];
            int b[] = new int[n];
            for (int i = 0 ; i < n ; i++) {
                a[i] = in.nextInt();
                b[i] = in.nextInt();
            }
	        int result = switchCount(a,b);
	        System.out.println(result);
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