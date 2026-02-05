class Solution {
    public int solution(int[][] triangle) {
        
        int len = triangle.length;
        int[][] dp = new int[len][len + 1]; 
        dp[0][1] = triangle[0][0]; 
        // print(triangle);

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= i + 1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j - 1];
            }
        }
        
        // print(dp);
        
        int result = 0;
        for (int j = 1; j <= len; j++) {
            result = Math.max(result, dp[len - 1][j]);
        }
        
        return result;
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