import java.util.*;

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        
        int cnt = 0; 

        int[] leftMin = new int[n];
        leftMin[0] = a[0]; 
        
        int[] rightMin = new int[n];
        rightMin[n - 1] = a[n - 1];
        
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], a[i]);
        }
        
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], a[i]);
        }
        
        for (int i = 0; i < n; i++) {
            if (leftMin[i] < a[i] && rightMin[i] < a[i]) continue; 
            
            cnt++;
        }
        
        return cnt;
    }
}