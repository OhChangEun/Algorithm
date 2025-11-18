class Solution {
    public int solution(int[] stones, int k) {
        int max = 0; 
        for (int stone: stones) {
            max = Math.max(max, stone);
        }
        
   		int answer = 0; 
        int left = 1; 
        int right = max;
        while (left <= right) {
            int mid = (left + right) / 2;
            System.out.println(mid);
            if (canCross(stones, mid, k)) {
                answer = mid; 
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    public boolean canCross(int[] stones, int mid, int k) {
        int count = 0; 
        for (int stone: stones) {
            if (stone - mid < 0) {
               count++;
            } else {
               count = 0;
            }
            if (count >= k) return false;
        }
        return true;
    }
}