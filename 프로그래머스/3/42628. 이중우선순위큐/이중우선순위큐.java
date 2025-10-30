import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
      
        for (String operation: operations) {
            String[] parts = operation.split(" ");
			String op = parts[0];
            int num = Integer.parseInt(parts[1]);
           
            if (op.equals("I")) {
               	maxHeap.add(num); 
               	minHeap.add(num); 
            } else if (op.equals("D")) {
               	if (minHeap.isEmpty()) continue;
                
                if (num == 1) {
                    int max = maxHeap.poll();
                    minHeap.remove(max);
                } else {
                    int min = minHeap.poll();
                    maxHeap.remove(min);
                }
            }
        }
        
        if (minHeap.isEmpty() && maxHeap.isEmpty()) {
            return new int[]{0, 0};
        } else {
            return new int[]{maxHeap.poll(), minHeap.poll()};
        }
    }
}