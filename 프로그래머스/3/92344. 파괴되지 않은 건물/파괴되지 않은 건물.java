class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        
        int[][] sum = new int[n + 1][m + 1];
        
        for (int[] s: skill) {
            int type = s[0]; 
            int r1 = s[1], c1 = s[2]; 
            int r2 = s[3], c2 = s[4]; 
            int degree = s[5]; 
     
            degree = type == 1 ? -degree : degree; 
            
            sum[r1][c1] += degree; 
            sum[r1][c2 + 1] -= degree;
            sum[r2 + 1][c1] -= degree;
            sum[r2 + 1][c2 + 1] += degree;
        }
        
        for (int row=0; row<n; row++) {
            for (int col=1; col<m; col++) {
                sum[row][col] += sum[row][col - 1];
            } 
        }
        
        for (int col=0; col<m; col++) {
            for (int row=1; row<n; row++) {
                sum[row][col] += sum[row - 1][col];
            } 
        }
        
        int result = 0;
        for (int row=0; row<n; row++) {
            for (int col=0; col<m; col++) {
                int value = board[row][col] + sum[row][col];
                if (value > 0) 
                    result++; 
            }
        }
        
        return result;
    }
}