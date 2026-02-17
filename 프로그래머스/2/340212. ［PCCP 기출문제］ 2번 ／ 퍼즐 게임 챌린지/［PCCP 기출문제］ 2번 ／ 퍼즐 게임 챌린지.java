class Solution {       
    final int MAX = Integer.MAX_VALUE; 
    
    public int solution(int[] diffs, int[] times, long limit) {
        // 현재 레벨 기준 최소값 구하기 
        int left = 1;
        int right = MAX - 1; 
        
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2; 
            
            long time = getTime(diffs,times, mid);
            if (time <= limit) {
                answer = mid; 
                right = mid - 1; 
            } else {
                left = mid + 1; 
            }
        }
        
        return answer;
    }
    
    // 해당 레벨로 모든 퍼즐을 푸는데 걸리는 시간 
    private long getTime(int[] diffs, int[] times, int level) {
        long result = 0; 
        
        int n = diffs.length; 
        for (int i = 0; i < n; i++) {
            if (diffs[i] <= level) {
                result += times[i];
            } else {
                int wrongTime = diffs[i] - level; 
                int currTime = times[i]; 
                int prevTime = (i == 0) ? 0 : times[i - 1];
                result += (currTime + prevTime) * wrongTime + currTime; 
            }
        }
        
        return result; 
    }
}