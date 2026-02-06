class Solution {
    int[][] map; 
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        map = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = cityMap[i - 1][j - 1];
            }
        }
        
        int[][] right = new int[m + 1][n + 1];
        int[][] down = new int[m + 1][n + 1];
        right[1][1] = 1;
        down[1][1] = 1; 
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue;
                
                int info = map[i][j];
                if (info == 1) continue; 
                
                if (info == 2) {
                    right[i][j] = (right[i][j] + right[i][j - 1]) % MOD;
                    down[i][j] = (down[i][j] + down[i - 1][j]) % MOD; 
                } else if (info == 0) {
                    right[i][j] = (right[i][j - 1] + down[i - 1][j]) % MOD;
                    down[i][j] = (right[i][j - 1] + down[i - 1][j]) % MOD; 
                }
            }
        }
        
        // print(right);
        return (right[m][n - 1] + down[m - 1][n]) % MOD;
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