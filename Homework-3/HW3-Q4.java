import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.BufferedReader;
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

	    public int largestTour(ArrayList<ArrayList<Integer>> adList) {
            int V = adList.size();
            int maxNodes = 0;
            for (int i = 0; i < V; i++) {
                int nodes = dfs(i, new boolean[V], adList);
                maxNodes = Math.max(maxNodes, nodes);
                
            }

            if (maxNodes == 1) return 1;
            
            return maxNodes - 1;
        }

        private int dfs(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adList) {
            visited[node] = true;
            int count = 1;
            for (int neighbor : adList.get(node)) {
                if (!visited[neighbor]) {
                    count += dfs(neighbor, visited, adList);
                }
            }
            return count;
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();

            ArrayList<ArrayList<Integer> > adjList
            = new ArrayList<ArrayList<Integer> >(n);

            for (int i = 0; i < n; i++)
                adjList.add(new ArrayList<Integer>());

            for (int i = 0 ; i < m ; i++) {
                int u,v;
                u = in.nextInt();
                v = in.nextInt();
                u--;
                v--;
                adjList.get(u).add(v);
            }
            System.out.println(largestTour(adjList));
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