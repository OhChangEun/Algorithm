import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
       
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int workTime: works) {
            maxHeap.add(workTime);
        }
        
        while (n>0) {
           	int maxWorkTime = maxHeap.poll();
           
            // works가 0을 maxHeap에 넣으면 안되므로 
            if (maxWorkTime > 1) {
                maxHeap.add(maxWorkTime-1);
            }
            n--;
            
            if (maxHeap.isEmpty()) {
                return 0;
            }
        }
        
        while (!maxHeap.isEmpty()) {
            int num = maxHeap.poll();
            answer += (long)(num * num);
        }
        
        return answer;
    }
}