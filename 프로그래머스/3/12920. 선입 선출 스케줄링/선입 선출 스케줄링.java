class Solution {
    public int solution(int n, int[] cores) {
        if (n <= cores.length) return n;
        
        n -= cores.length;
        
        int left = 0;
        int right = 10000 * n; 
        int targetTime = 0;
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
       
        // System.out.println(targetTime);
        int completedBeforeJobs = countCompletedJobs(targetTime - 1, cores);
        // System.out.println(completedBeforeJobs);
        for (int i=0; i<cores.length; i++) {
            if (targetTime % cores[i] == 0) {
            	completedBeforeJobs++;
               	if (completedBeforeJobs == n) {
                    return i + 1;
                } 
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