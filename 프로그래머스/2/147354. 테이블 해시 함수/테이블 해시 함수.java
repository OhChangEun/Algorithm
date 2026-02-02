import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int n = data[0].length;
        
        Arrays.sort(data, (a, b) -> {
            if (a[col - 1] != b[col - 1]) 
                return a[col - 1] - b[col - 1]; 
            return b[0] - a[0];
        });
      
        int xorResult = 0;
        for (int r = row_begin; r <= row_end; r++) {
            int sum = 0;
            for (int c = 0; c < n; c++) {
                sum += data[r - 1][c] % r;
            }

            xorResult ^= sum;
        }
       
        return xorResult; 
    }
}