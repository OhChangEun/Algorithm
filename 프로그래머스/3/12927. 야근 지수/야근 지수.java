import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        
        // 10 9 8 2 2 1
        // 전체적으로 숫자가 낮아져야 제곱의 합이 낮아짐
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        
        for (int work: works) {
            pq.offer(work);
        }
        
        for (int i = 0; i < n; i++) {
            if (pq.isEmpty()) 
                return 0; 
            
            int num = pq.poll() - 1;
            if (num != 0) 
            	pq.offer(num);
        }
        
        long answer = 0;
        while (!pq.isEmpty()) {
            int num = pq.poll();
            answer += num * num; 
        }
        
        return answer;
    }
    
    private void print(Object o) {
        System.out.println(o);
    }
    
}