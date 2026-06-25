class Solution {
    public int solution(int[][] triangle) {
        // 7 
        // 3 8 
        // 8 1 0 
        // 2 7 4 4
        // 4 5 2 6 5
        
        // i 
        // i + 1, j, j + 1
        
        int n = triangle.length; 
        
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }
        
        return triangle[0][0];
    }
}