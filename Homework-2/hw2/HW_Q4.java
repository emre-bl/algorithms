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
        public int[] countingSort(int[] arr) {

            int max = findMax(arr);
            int[] c = new int[max + 1];
            for (int num : arr) {
                c[num]++;
            }
            for (int i = 1; i < c.length; i++) {
                c[i] = c[i - 1] + c[i];
            }
            int[] sortedArray = new int[arr.length];
            int curr, sortedIndex;
            for (int in = arr.length - 1; in >= 0; in--) {
                curr = arr[in];
                c[curr]--;
                sortedIndex = c[curr];
                sortedArray[sortedIndex] = curr;
            }
            return sortedArray;
        }

        private int findMax(int[] arr) {
            int max = arr[0];
            for (int num : arr) {
                if (num > max) {
                    max = num;
                }
            }
            return max;
        }

        public int[] findNumbers(int n, int k, int l, int[] arr) {
            int sortedArr[] = new int[n];
            sortedArr = countingSort(arr);
            int begin = k - (l / 2);
            int end = k + (l / 2);
            int resArrray[] = new int[l];
            for (int i = begin; i < end; i++) {
                resArrray[i - begin] = sortedArr[i];
            }
            return resArrray;
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int l = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = in.nextInt();

            int[] result;
            result = findNumbers(n, k, l, arr);

            for (int i = 0; i < l; i++) {
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
