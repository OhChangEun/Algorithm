import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());    
        for (int i=0; i<works.length; i++) {
       		maxHeap.add(works[i]); 
        }
        while (n>0) {
            int max = maxHeap.poll();
            if (max-1 > 0)
                maxHeap.add(max-1);
            n--;
            
            if (maxHeap.isEmpty()) {
           		return 0;     
            }
        }
       	
        while (!maxHeap.isEmpty()) {
            int num = maxHeap.poll();
            answer += num * num;
        } 
        
        return answer;
    }
}