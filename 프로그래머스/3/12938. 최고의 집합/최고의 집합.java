import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {};
        
        if (n > s) {
            return new int[] {-1};
        }
        
        // 집합 원소의 개수: n
        // 모든 원소의 합: s 
        answer = new int[n];
        
        for (int i=0; i<n; i++) {
            answer[i] = s / n; // 11/3 = 3...2
        }
       
        int value = s % n;
        for (int i=0; i<value; i++) {
            answer[i]++;
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
}