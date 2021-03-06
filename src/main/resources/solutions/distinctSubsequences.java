public class Solution {
    public int numDistinct(String S, String T) {
        int si = S.length(), ti = T.length();
        if (si <= 0 || ti <= 0 || si < ti) return 0;

        int[][] dptable = new int[si][ti];
        dptable[0][0] = S.charAt(0) == T.charAt(0) ? 1 : 0;
        for (int j = 0; j < ti; j++) {
            for (int i = 1; i < si; i++) {
                dptable[i][j] = dptable[i-1][j];
                if (S.charAt(i) == T.charAt(j)) {
                    dptable[i][j] += j == 0 ? 1 : dptable[i - 1][j - 1];
                }
            }
        }
        return dptable[si - 1][ti - 1];
    }
}