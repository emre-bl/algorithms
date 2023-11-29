import java.util.ArrayList;

public class Q1 {
    public static double bookPlacement (double [] thickness, double L) {
        
        double total_thickness = 0;
        for (int i = 0; i < thickness.length; i++) total_thickness += thickness[i];

        int required_shelves = (int) Math.ceil(total_thickness / L);

        System.out.println("required_shelves: " + required_shelves);
        System.out.println("total_thickness: " + total_thickness);

        ArrayList<ArrayList<Double>> shelves = new ArrayList<ArrayList<Double>>();

        

        return 1.0;
    }

    public static void main(String[] args) {
        double[] thickness = {0.1, 0.2, 0.3, 0.4, 0.5};
        double L = 1.0;
        bookPlacement(thickness, L);
    }


}
