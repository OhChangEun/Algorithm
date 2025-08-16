import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
       
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for (String operation: operations) {
            String[] parts = operation.split(" "); // 공백으로 파싱
            String op = parts[0];
            int num = Integer.parseInt(parts[1]);
            
            if (op.equals("I")) {
                minHeap.add(num);
                maxHeap.add(num);
            } else if (op.equals("D")) {
                if (minHeap.isEmpty()) continue;
               
                if (num == -1) {
            		int min = minHeap.poll();
                    maxHeap.remove(min);
                } else if (num == 1) {
            		int max = maxHeap.poll();
                    minHeap.remove(max);
                }
            }
        }
        if (minHeap.isEmpty() && maxHeap.isEmpty()) {
            answer = new int[] {0, 0};
        } else {
            answer = new int[] {maxHeap.peek(), minHeap.peek()};
            // System.out.println(minHeap);
        }
            
        return answer;
    }
}