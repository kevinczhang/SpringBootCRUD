public class Solution {
    static final int M = 1000000007;
    public int checkRecord(int n) {
        // ending with P or L, no A
        long[] PorL = new long[n + 1];
        // ending with P, no A
        long[] P = new long[n + 1];
        PorL[0] = P[0] = 1; PorL[1] = 2; P[1] = 1;

        for (int i = 2; i <= n; i++) {
            P[i] = PorL[i - 1];
            PorL[i] = (P[i] + P[i - 1] + P[i - 2]) % M;
        }

        long res = PorL[n];
        // inserting A into (n-1)-length strings
        for (int i = 0; i < n; i++) {
    	    long s = (PorL[i] * PorL[n - i - 1]) % M;
            res = (res + s) % M;
        }
        return (int) res;
    }
}