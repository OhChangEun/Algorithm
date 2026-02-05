import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
      
        for (String operation: operations) {
            String[] parts = operation.split(" ");
            
            String op = parts[0];
            if (op.equals("I")) { // 추가 
                int num = Integer.parseInt(parts[1]);
                maxHeap.offer(num);
                minHeap.offer(num);
            } else { // 삭제 
                if (maxHeap.isEmpty()) continue; 
                
                if (parts[1].startsWith("-")) {
                    int min = minHeap.poll();
                    maxHeap.remove(min);
                } else {
                    int max = maxHeap.poll();
                    minHeap.remove(max);
                }
            }
        }
      
        return maxHeap.isEmpty() ? new int[] {0, 0} : new int[] {maxHeap.poll(), minHeap.poll()};
    }
}