class Solution {
    public int solution(int[] stones, int k) {
       
        int result = 0;
        
        int left = 0;
       	int right = 200_000_000; 
        while (left <= right) {
       		int mid = (left + right) / 2; 
            
            if (canCross(stones, mid, k)) {
                result = mid; 
                left = mid + 1;
            } else {
                right = mid - 1; 
            }
        }
        
        return result;
    }
    
    private boolean canCross(int[] stones, int mid, int k) {
        // mid 명이 연속으로 k개의 수가 0 미만 되면 stones를 건널 수 없다 
        int cnt = 0; 
        for (int stone: stones) {
            if (stone - mid < 0) {
                cnt++;
                
                if (cnt == k) return false; 
            } else {
                cnt = 0; // 연속되지 않으므로 초기화
            }
        }
        
 		return true; 
    }
}