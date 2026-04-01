import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = alp;
        int maxCop = cop; 
        
        for (int[] p: problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }
        
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i <= maxAlp; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        dp[alp][cop] = 0;
        
        for (int a = alp; a <= maxAlp; a++) {
            for (int c = cop; c <= maxCop; c++) {
                if (dp[a][c] == Integer.MAX_VALUE) continue; 
                
                if (a + 1 <= maxAlp) {
                    dp[a + 1][c] = Math.min(dp[a + 1][c], dp[a][c] + 1);
                }
                
                if (c + 1 <= maxCop) {
                    dp[a][c + 1] = Math.min(dp[a][c + 1], dp[a][c] + 1);
                }
                
                for (int[] p: problems) {
                    int alpReq = p[0];
                    int codReq = p[1];
                    int alpRew = p[2];
                    int codRew = p[3];
                    int cost = p[4];
                    
                    if (a >= alpReq && c >= codReq) {
                        int nextAlp = Math.min(maxAlp, a + alpRew);
                        int nextCod = Math.min(maxCop, c + codRew);
                        dp[nextAlp][nextCod] = Math.min(dp[nextAlp][nextCod], dp[a][c] + cost);
                    }
                }
            }
        }
        
        //print(dp);
        
        return dp[maxAlp][maxCop];
    }
    
    public void print(int[][] arr) {
        for (int[] row: arr) {
            for (int num: row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}