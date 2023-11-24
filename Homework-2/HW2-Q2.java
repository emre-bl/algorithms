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
 
    static class TaskQ2 {

        static InputReader _in;

        public static int[] findAltitude(int maxAltitude) {
                int k, numberOfExperiments;
                int shift = find_first_shift(maxAltitude);
                int[] result = run(0, maxAltitude, 0, shift);
                k = result[0];
                numberOfExperiments = result[1];

                return new int[]{k, numberOfExperiments};
        }

        public static boolean isSurvived(int altitude) {
            System.out.println(1 + " " + altitude);
            System.out.flush();
            return _in.nextInt() == 0;
        }

        public static void checkAltitude(int altitude) {
            System.out.println(2 + " " + altitude);
            System.out.flush();  
        }

        public void solve(InputReader in, PrintWriter out) {
            _in = in;
            int n = in.nextInt();
            int[] result;
            result = findAltitude(n);
            checkAltitude(result[0]);
        }

        public static int[] run(int sindex, int known, int tries, int shift) {
            tries++;
            int newS = sindex + shift;
            if (isSurvived(newS)) {
                return run(newS, known, tries, shift-1);
            } else {
                int i;
                for (i = sindex + 1; i < newS; i++) {
                    tries++;
                    if (!isSurvived(i)) {
                        return new int[]{i-1,tries};
                    }
                }
                return new int[]{i,tries};
            }
        }

        public static int find_first_shift(int n) {
            for (int i = 1; i < n; i++) {
                int sum = 0;
                for(int j = 1; j <= i; j++) {
                    sum+=j;
                }
                if (sum >= n) {
                    return i;
                }
            }
            return -1;
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

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskQ2 solver = new TaskQ2();
        solver.solve(in, out);
        out.close();
    }
}