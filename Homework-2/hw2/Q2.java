import java.util.Random;

class Q2 {
    int k = 50;

    public int[] findAltitute(int N, int K) {
        k = K;
        int shift = find_first_shift(N);
        return run(0, K, N, 0, shift);
    }

    public int[] run(int sindex, int eindex, int known, int tries, int shift) {
        tries++;
        if (sindex == eindex) {
            int res[] = {sindex, tries};
            return res;
        }
            
        int newS = sindex + shift;
        if (isSurvived(newS)) {
            System.out.println("New S: " + newS + " Shift: " + shift + " Tries: " + tries);
            return run(newS, eindex, known, tries, shift-1);
        } else {
            int i;
            for (i = sindex + 1; i < newS; i++) {
                tries++;
                if (!isSurvived(i)) {
                    int[] res = {i-1, tries};
                    return res;
                }
            }
            int res[] = {i, tries};
            return res;
        }
    }

    public boolean isSurvived(int altitude) {
        return altitude <= k;
    }

    public int find_first_shift(int n) {
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

    public static void main(String[] args) {
        Q2 obj = new Q2();
        int max = 0;
        for(int i = 1; i <= 100; i++) {
            int[] res = obj.findAltitute(100, i);
            System.out.println("k: " + i + " Altitude: " + res[0] + " Tries: " + res[1]);
            if (res[1] > max) {
                max = res[1];
            }
            System.out.println("--------------------");
        }

        System.out.println("Max: " + max);
    }
}