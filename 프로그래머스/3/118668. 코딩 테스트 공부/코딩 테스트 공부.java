import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlgo = 0;
        int maxCode = 0;
        for (int[] p: problems) {
            maxAlgo = Math.max(maxAlgo, p[0]);
            maxCode = Math.max(maxCode, p[1]);
        }
        
        alp = Math.min(maxAlgo, alp);
        cop = Math.min(maxCode, cop);
        
        int[][] dp = new int[maxAlgo + 1][maxCode + 1];
        for (int i = 0; i <= maxAlgo; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        dp[alp][cop] = 0;
        for (int a = alp; a <= maxAlgo; a++) {
            for (int c = cop; c <= maxCode; c++) {
                if (dp[a][c] == Integer.MAX_VALUE) continue;
                
                if (a + 1 <= maxAlgo) {
                    dp[a + 1][c] = Math.min(dp[a + 1][c], dp[a][c] + 1);
                }
                
                if (c + 1 <= maxCode) {
                    dp[a][c + 1] = Math.min(dp[a][c + 1], dp[a][c] + 1);
                }
                
                for (int[] p: problems) {
                    int reqA = p[0];
                    int reqC = p[1];
                    int rewardA = p[2];
                    int rewardC = p[3];
                    int cost = p[4];
                    
                    if (a >= reqA && c >= reqC) {
                        int na = Math.min(a + rewardA, maxAlgo);
                        int nc = Math.min(c + rewardC, maxCode);
                        dp[na][nc] = Math.min(dp[na][nc], dp[a][c] + cost);
                    }
                }
                
            }
        }
        
        return dp[maxAlgo][maxCode];
    }
}