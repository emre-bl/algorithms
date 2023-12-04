import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Q1 {
    public static double bookPlacement(double[] thickness, double L) {
        int n = thickness.length; // number of books
        double[] shelves = new double[n]; // thickness of each shelf 
        double maxUnusedLength = 0; // maximum unused length

        for (int i = 0; i < n; i++) { // for each book
            int j = 0; // index of the shelf with minimum thickness
            double minShelfThickness = shelves[0]; 

            for (int k = 1; k < n; k++) {
                if (shelves[k] < minShelfThickness) {
                    j = k;
                    minShelfThickness = shelves[k];
                }
            }

            shelves[j] += thickness[i];
            double unusedLength = L - shelves[j];
            if (unusedLength > maxUnusedLength) {
                maxUnusedLength = unusedLength;
            }
        }

        return maxUnusedLength;
    }

    public static double bookPlacement2(double[] thickness, double L) {
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
      

        double last_gap = changeBooks(shelves, L, requiredShelves);
        int bool = 1;
        while(bool == 1) {
            bool = changeBooks(shelves, L, requiredShelves);
        }
        
        double max_gap = 0;
        for (int i = 0; i < requiredShelves; i++) {
            double gap = shellSpace(shelves, i, L);
            if (gap > max_gap) {
                max_gap = gap;
            }
        }

        for (int i = 0; i < requiredShelves; i++) {
            System.out.println(shelves.get(i) +" " + shellSpace(shelves, i, L));
        }
        return max_gap;
    }

    public static boolean isShelfFull(ArrayList<ArrayList<Double>> shelves, int shelfIndex, double L) {
        double total_thickness = 0;
        for (int i = 0; i < shelves.get(shelfIndex).size(); i++) {
            total_thickness += shelves.get(shelfIndex).get(i);
        }
        return total_thickness == L;
    }

    public static double shellSpace(ArrayList<ArrayList<Double>> shelves, int shelfIndex, double L) {
        double total_thickness = 0;
        for (int i = 0; i < shelves.get(shelfIndex).size(); i++) {
            total_thickness += shelves.get(shelfIndex).get(i);
        }
        return L - total_thickness;
    }

    public static boolean isThereShell(ArrayList<ArrayList<Double>> shelves, double thickness, double L) {
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

    public static int changeBooks(ArrayList<ArrayList<Double>> shelves, double L, int requiredShelves) {
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

    public static void main(String[] args) {
        double[] thickness = {16.38 ,17.47 ,7.75, 23.68, 2.85, 10.22, 11.62, 12.65};
        double L = 93.63;        ;
        System.out.println(bookPlacement2(thickness, L));
    }

}
