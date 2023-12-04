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
        public static double bookPlacement(double[] thickness,double L) {
            int n = thickness.length; // number of books
            double total_thickness = 0;
            for (int i = 0; i < n; i++) total_thickness += thickness[i]; // total thickness of all books
            int requiredShelves = (int)Math.ceil(total_thickness / L); // number of shelves required

            ArrayList<ArrayList<Double>> shelves = new ArrayList<>(); 
            for (int i = 0; i < requiredShelves; i++) {
                shelves.add(new ArrayList<>());
            }

            for(int i = 0; i < n; i++) {
                boolean is_there_shell = isThereShell(shelves, thickness[i], L);

                if (!is_there_shell) { // if there is no shell for this book we need to add a new shelf
                    // add new shelf
                    shelves.add(new ArrayList<>());
                    requiredShelves++;
                    shelves.get(requiredShelves - 1).add(thickness[i]);
                    continue;
                }

                for(int j = 0; j < requiredShelves; j++) { // find the first shelf that can fit the book
                    if (isShelfFull(shelves, j, L)) { // if shelf is full continue
                        continue;
                    } 
                    if (shellSpace(shelves, j, L) < thickness[i]) { // if shell space is not enough
                        continue;
                    }
                    shelves.get(j).add(thickness[i]);
                    break;
                }
            }
        

            double last_gap = changeBooks(shelves, L, requiredShelves); // change books until no more changes can be made
            int bool = 1;
            while(bool == 1) { // change books until no more changes can be made
                bool = changeBooks(shelves, L, requiredShelves);
            }
            
            double max_gap = 0; // find the maximum gap
            for (int i = 0; i < requiredShelves; i++) {
                double gap = shellSpace(shelves, i, L);
                if (gap > max_gap) {
                    max_gap = gap;
                }
            }
            return max_gap;
        }

        public static boolean isShelfFull(ArrayList<ArrayList<Double>> shelves, int shelfIndex, double L) { // check if shelf is full
            double total_thickness = 0;
            for (int i = 0; i < shelves.get(shelfIndex).size(); i++) {
                total_thickness += shelves.get(shelfIndex).get(i);
            }
            return total_thickness == L;
        }

        public static double shellSpace(ArrayList<ArrayList<Double>> shelves, int shelfIndex, double L) { // find the shell space of a shelf
            double total_thickness = 0;
            for (int i = 0; i < shelves.get(shelfIndex).size(); i++) {
                total_thickness += shelves.get(shelfIndex).get(i);
            }
            return L - total_thickness;
        }

        public static boolean isThereShell(ArrayList<ArrayList<Double>> shelves, double thickness, double L) { // check if there is a shell for a book
            for (int i = 0; i < shelves.size(); i++) {
                if (isShelfFull(shelves, i, L)) {
                    continue;
                } 
                if (shellSpace(shelves, i, L) < thickness) {
                    continue;
                }
                return true;
            }
            return false;
        }

        public static int changeBooks(ArrayList<ArrayList<Double>> shelves, double L, int requiredShelves) { // change books if possible
            double max_gap = 0;
            int max_gap_index = 0;
            for (int j = 0; j < requiredShelves; j++) {
                double gap = shellSpace(shelves, j, L);
                if (gap > max_gap) {
                    max_gap = gap;
                    max_gap_index = j;
                }
            }
            double min_book = 1000000000;
            int min_book_shelf_index = 0;
            int min_book_index = 0;
            for (int j = 0; j < requiredShelves; j++) {
                if (j == max_gap_index) continue;
                for (int i = 0; i < shelves.get(j).size(); i++) {
                    if (shelves.get(j).get(i) < min_book) {
                        min_book = shelves.get(j).get(i); 
                        min_book_shelf_index = j;
                        min_book_index = i;
                    }
                }
            }
            double shell_space = shellSpace(shelves, min_book_shelf_index, L);
            if (shell_space + min_book > max_gap) {
                return 0;
            }
            shelves.get(min_book_shelf_index).remove(min_book_index);
            shelves.get(max_gap_index).add(min_book);
            return 1;
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            double L = in.nextDouble();
            double[] thickness = new double[n];
            for (int i = 0 ; i < n ; i++)
                thickness[i] = in.nextDouble();
            System.out.println(bookPlacement(thickness, L)); 
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