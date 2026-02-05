import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        
        int div = s / n; 
        if (div == 0) {
            return new int[] {-1};
        }
        int mod = s % n; 
        
       	int[] result = new int[n]; 
        Arrays.fill(result, div);
        
        int i = n - 1;
        while (mod-- > 0) {
            result[i]++;
            i--;
        }
        
        return result; 
    }
}