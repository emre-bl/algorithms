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
        public static double letsGetRich(double[] p,int k) {
            int n = p.length;
            if (n < 2 || k == 0) return 0;
        
            double[] dp = new double[n];
        
            for (int i = 0; i < k; i++) {
                double maxProfit = -p[0]; // Initialize with the loss from buying the first stock
                double prev = 0; // To keep track of the previous dp[j]
        
                for (int j = 1; j < n; j++) {
                    double temp = dp[j]; // Store the current dp[j] before updating it
                    dp[j] = Math.max(dp[j - 1], p[j] + maxProfit);
                    maxProfit = Math.max(maxProfit, prev - p[j]); // Update maxProfit
                    prev = temp; // Update prev for the next iteration
                }
            }
        
            return dp[n - 1] * 1000;
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            double[] p = new double[n];
            for (int i = 0 ; i < n ; i++)
                p[i] = in.nextDouble();
            System.out.println(letsGetRich(p, k)); 
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