import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
 
        int left = 0;
        int right = Arrays.stream(stones).max().getAsInt();
       
        while (left <= right) {
            int mid = (left + right) / 2;
           
            if (pass(stones, mid, k)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    public boolean pass(int[] stones, int mid, int k) {
        int count = 0; // mid 명이 건널 수 없는 연속되는 돌 개수
        
        for (int stone: stones) {
            if (stone - mid < 0) {
                count++;
            } else {
                count = 0;
            }
            
            if (count >= k) 
                return false;
        }
        return true;
    }
}