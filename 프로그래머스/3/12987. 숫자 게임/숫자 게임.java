import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int n = A.length; 
        Arrays.sort(A);
        Arrays.sort(B);
        
        int winCnt = 0;
        int right = 0; 
        for (int left = 0; left < n; left++) {
            while (right < n && A[left] >= B[right]) {
                right++;
            } 
            
            if (right < n && A[left] < B[right]) {
                winCnt++;
                right++;
            }
        }
        
        return winCnt;
    }
}