public class Q2 {
    public static void main(String[] args) {
        double[] p = {0.29,0.75,0.54,0.68,0.86};
        int k = 1;
        System.out.println(letsGetRich2(p, k));
  
    }

    public static double letsGetRich(double[] p, int k) {
        int n = p.length;
        double[][] dp = new double[k + 1][n];

        for (int i = 1; i <= k; i++) {
            double maxProfit = Double.NEGATIVE_INFINITY;
            for (int j = 1; j < n; j++) {
                maxProfit = Math.max(maxProfit, dp[i - 1][j - 1] - p[j - 1]);
                dp[i][j] = Math.max(dp[i][j - 1], p[j] + maxProfit);
            }
        }

        return 1000 * dp[k][n - 1];
    }

    public static double letsGetRich2(double[] p, int k) {
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
}