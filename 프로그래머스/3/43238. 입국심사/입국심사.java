class Solution {
    public long solution(int n, int[] times) {
        long max = 0;
        for (int time: times) {
            max = Math.max(max, time);
        }
        
        long answer = 0;
        long left = 0;
        long right = n * max;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (canFinish(times, mid, n)) { 
    			answer = mid;   
      			right = mid - 1;         
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    public boolean canFinish(int[] times, long mid, int n) {
        long total = 0;
        for (long time: times) {
            total += mid / time;
        }
        
        if (total >= n) {
           	return true; 
        }
        return false;
    }
}