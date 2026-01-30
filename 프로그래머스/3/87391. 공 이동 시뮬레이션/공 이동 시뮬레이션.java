class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long rowMin = x, rowMax = x; 
        long colMin = y, colMax = y; 
        
        for (int i = queries.length - 1; i >= 0; i--) {
            int cmd = queries[i][0];
            int dx = queries[i][1]; 
            
            if (cmd == 0) {
                if (colMin != 0) {
                    colMin += dx;
                }
                colMax = Math.min(m - 1, colMax + dx);
            } else if (cmd == 1) {
                if (colMax != m - 1) {
                    colMax -= dx; 
                }
                colMin = Math.max(0, colMin - dx);
            } else if (cmd == 2) {
                if (rowMin != 0) {
                    rowMin += dx; 
                }
                rowMax = Math.min(n - 1, rowMax + dx);
            } else if (cmd == 3) {
                if (rowMax != n - 1) {
                    rowMax -= dx; 
                }
                rowMin = Math.max(0, rowMin - dx);
            }
            
            if (colMin >= m || colMax < 0 || rowMin >= n || rowMin < 0) 
                return 0; 
        }
        
        
        return (rowMax - rowMin + 1) * (colMax - colMin + 1);
    }
}