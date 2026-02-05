class Solution {    
    public long solution(int n, int[] times) {
        
        long max = 0;
        for (int time: times) {
            max = Math.max(max, time);
        }
        
        long result = 0;
        long left = 0; 
        long right = max * n; 
        while (left <= right) {
        	long mid = (left + right) / 2;
            // System.out.println(mid);
            
            if (canFinish(times, n, mid)) {
        		result = mid; 
                right = mid - 1; 
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    private boolean canFinish(int[] times, int n, long mid) {
        // mid 시간 동안 끝낼 수 있는 작업 개수의 합
       	long totalFinish = 0;
        for	(int time: times) {
            totalFinish += mid / time; 
        } 
        
        return totalFinish >= n; // 끝낸 작업이 n개 이상이면
    }
}