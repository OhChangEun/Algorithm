class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        int[][] dp = new int[n+1][m+1];
      	
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                dp[i][j] = -1;
            }
        }
        
        // 물에 잠긴 지역 0으로 
        for (int k=0; k<puddles.length; k++) {
           	int waterX = puddles[k][1];
            int waterY = puddles[k][0];
        	
            dp[waterX][waterY] = 0;
        }

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
               	if (i==1 && j==1) 
                	dp[i][j] = 1;
                else { 
					if (dp[i][j] != 0) {
                        dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1_000_000_007;
                    } 
                }
            }
        }
        
//        for (int i=0; i<=n; i++) {
//           for (int j=0; j<=m; j++) {
//               System.out.print(dp[i][j] + " ");
//           }
//          System.out.println();
//     }
        return dp[n][m];
    }
}