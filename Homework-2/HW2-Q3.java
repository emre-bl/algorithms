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
import java.util.Collections;
import java.util.ArrayList;

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
        public static double[] bucketSort(double[] arr) {
            int n = arr.length;
            ArrayList<Double>[] buckets = new ArrayList[16];

            for (int i = 0; i < 16; i++) {
                buckets[i] = new ArrayList<Double>();
            }

            for (int i = 0; i < n; i++) {
                int bucketIndex = 0;
                if (arr[i] < 0.6) {
                    bucketIndex = 0;
                } else if (arr[i] < 0.7) {
                    bucketIndex = 1;
                } else if (arr[i] < 0.9) { // bura hatalı
                    bucketIndex = (int) ((Math.ceil((arr[i] - 0.7) * 100)) / 2 + 1) ;
                } else if (arr[i] < 0.925) {
                    bucketIndex = 12; 
                } else if (arr[i] < 0.95) {
                    bucketIndex = 13;
                } else if (arr[i] < 0.975) {
                    bucketIndex = 14;
                } else {
                    bucketIndex = 15;
                }

                buckets[bucketIndex].add(arr[i]);
            }

            for (int i = 0; i < 16; i++) {
                if (buckets[i].size() <= 50) insertionSort(buckets[i]);
                else quickSort(buckets[i], 0, buckets[i].size() - 1);
            }

            int index = 0;
            for(int i = 0; i < 16; i++) {
                for(int j = 0; j < buckets[i].size(); j++) {
                    arr[index++] = buckets[i].get(j);
                }
            }

            return arr;
        }

        public static void insertionSort(ArrayList<Double> arr) {
            int n = arr.size();
            for (int i = 1; i < n; ++i) {
                double key = arr.get(i);
                int j = i - 1;
                while (j >= 0 && arr.get(j) > key) {
                    arr.set(j + 1, arr.get(j));
                    j = j - 1;
                }
                arr.set(j + 1, key);
            }
        }

        public static void quickSort(ArrayList<Double> arr, int low, int high) {
            if (low < high) {
                int pi = partition(arr, low, high);

                quickSort(arr, low, pi - 1);
                quickSort(arr, pi + 1, high);
            }
        }

        public static int partition(ArrayList<Double> arr, int low, int high) {
            double pivot = arr.get(high);
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (arr.get(j) < pivot) {
                    i++;

                    double temp = arr.get(i);
                    arr.set(i, arr.get(j));
                    arr.set(j, temp);
                }
            }

            double temp = arr.get(i + 1);
            arr.set(i + 1, arr.get(high));
            arr.set(high, temp);

            return i + 1;
        }

        public void solve(InputReader in, PrintWriter out) {
            int t = in.nextInt();
            for (int testCase = 0 ; testCase < t ; testCase++) {
                int n = in.nextInt();
                double[] array = new double[n];
                for (int i = 0 ; i < n ; i++)
                    array[i] = in.nextDouble();
                array = bucketSort(array);
                for (int i = 0 ; i < n ; i++) {
                    out.print(array[i]);
                    out.print(' ');
                }
                out.println();
            }
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

        public double nextDouble() {
            return Double.parseDouble(next());
        }
 

    }
}