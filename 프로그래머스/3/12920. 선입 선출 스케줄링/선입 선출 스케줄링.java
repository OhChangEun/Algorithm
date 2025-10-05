class Solution {
    public int solution(int n, int[] cores) {
        
        // 작업시간 0에 작업을 배치를 다 할 수 있으면 
        if (n <= cores.length) {
            return n;
        }
        
        // 작업시간 0에 모든 작업 배치 
        n -= cores.length;
       
        // 작업시간 찾기 
        int left = 0;
        int right = n * 10000; // 최장 작업시간 
        int targetTime = 0; // 작업에 걸리는 시간 
        
        while (left <= right) {
            int mid = (left + right) / 2;
            int completedJobs = countCompletedJobs(mid, cores);

            if (completedJobs >= n) {
                targetTime = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
     
        int completedBeforeTarget = countCompletedJobs(targetTime - 1, cores);
        for (int i=0; i<cores.length; i++) {
            if (targetTime % cores[i] == 0) {
                completedBeforeTarget++;
            }
            
            if (completedBeforeTarget == n) {
                return i + 1;
            }
        }
        
        return -1;
    }
    
    private int countCompletedJobs(int time, int[] cores) {
        int count = 0;
        for (int core: cores) {
            count += time / core;
        }
        return count;
    }
}