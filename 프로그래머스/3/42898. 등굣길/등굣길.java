class Solution {
    final int MOD = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n + 1][m + 1];
        
        boolean[][] isPuddle = new boolean[n + 1][m + 1]; 
        for (int[] puddle: puddles) {
            int x = puddle[0];
            int y = puddle[1]; 
            isPuddle[y][x] = true; 
        }
        
        dp[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (!isPuddle[i][j]) {
                	dp[i][j] += (dp[i - 1][j] + dp[i][j - 1]) % MOD;
                }
            }
        }
        
        // print(dp);
        return dp[n][m] % MOD;
    }
    
    private void print(int[][] arr) {
        for (int[] row: arr) {
            for (int num: row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}