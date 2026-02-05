import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
       
        int cnt = 0;
        int prevCamera = -30001;
        for (int[] route: routes) {
            int start = route[0]; 
            int end = route[1];
            
            if (prevCamera < start) {
                cnt++;
                prevCamera = end; 
            }
        }
        
        return cnt;
    }
    
    private void print(int[][] routes) {
        for (int[] row: routes) {
            for (int num: row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}