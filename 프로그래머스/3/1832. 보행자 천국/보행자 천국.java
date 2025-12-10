class Solution {
    static final int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int[][] right = new int[m + 1][n + 1];
        int[][] down = new int[m + 1][n + 1];

        right[1][1] = 1;
        down[1][1] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue;

                int info = cityMap[i - 1][j - 1];

                if (info == 1) { // 막힘
                    right[i][j] = 0;
                    down[i][j] = 0;
                } 
                else if (info == 0) { // 자유
               		int total = (right[i][j - 1] + down[i - 1][j]) % MOD;
                    right[i][j] = total;
                    down[i][j] = total;
                } 
                else { // 직진만 가능 (2)
                    right[i][j] = right[i][j - 1];
                    down[i][j] = down[i - 1][j];
                }
            }
        }

        return (right[m][n - 1] + down[m - 1][n]) % MOD;
    }
}
