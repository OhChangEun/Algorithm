import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
     	Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        
        for (int num: numbers) {
       		int size = queue.size(); 
            for (int i=0; i<size; i++) {
                int curr = queue.poll();
                queue.offer(curr + num);
                queue.offer(curr - num);
            }
        }
      
        int count = 0;
        while (!queue.isEmpty()) {
           	int num = queue.poll();
            if (num == target) 
                count++;
        }
        
        return count;
    }
}