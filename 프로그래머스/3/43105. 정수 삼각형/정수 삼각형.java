import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
      
        int size = triangle.length - 2;
        
        for (int i=size; i>=0; i--) {
            for (int j=0; j<=i; j++) {
            	triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
            }
        } 
        
        return triangle[0][0];
    }
}