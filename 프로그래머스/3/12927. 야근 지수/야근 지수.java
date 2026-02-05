import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int work: works) {
            pq.offer(work);
        }
        
        while (n-- > 0) {
            if (pq.isEmpty()) return 0; 

            int max = pq.poll();
            max--; 
            
            if (max != 0) 
                pq.offer(max);
        }
        
        long result = 0;
        while (!pq.isEmpty()) {
            int num = pq.poll();
            result += num * num;
        } 
        
        return result;
    }
}