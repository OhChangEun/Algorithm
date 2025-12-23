class Solution {
    public int solution(int n, int[] cores) {
        int maxCore = 0; 
        for (int core: cores) {
            maxCore = Math.max(maxCore, core);
        }
      
        n -= cores.length; 
        
        int finishedTime = 0;
       
        int left = 0;
        int right = maxCore * n; 
        while (left <= right) {
            int mid = (left + right) / 2; 
            int finishedJob = getFinishedJob(cores, mid);
            
            if (finishedJob >= n) {
                finishedTime = mid; 
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        int result = 0;
        int prevFinishedJob = getFinishedJob(cores, finishedTime - 1);
        for (int i = 0; i < cores.length; i++) {
            if (finishedTime % cores[i] == 0) {
                prevFinishedJob++;
                if (prevFinishedJob == n) {
                    result = i + 1; 
                    break; 
                }    
            }
        }
        return result; 
    }
    
    private int getFinishedJob(int[] cores, int time) {
        int total = 0; 
        for (int core: cores) {
            total += time / core;
        }
        return total;
    }
}