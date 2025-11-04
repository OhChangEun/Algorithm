class Solution {
    public int solution(int m, int n, int[][] puddles) {
   		int[][] dp = new int[n + 1][m + 1];
        boolean[][] isPuddle = new boolean[n + 1][m + 1];
		int MAX  = 1_000_000_007;
        // 웅덩이 위치 초기화 
        for (int[] puddle: puddles) {
            int x = puddle[0]; 
            int y = puddle[1];
            isPuddle[y][x] = true;
        }
        
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = 1;
                    continue;
                }
                
                if (!isPuddle[i][j]) {
                   	dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MAX; 
                }
            }
        }
        return dp[n][m];
    }
}