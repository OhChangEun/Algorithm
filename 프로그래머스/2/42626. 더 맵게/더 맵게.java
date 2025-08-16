import java.util.*;

class Solution {
    public int calcScoville(int first, int second) {
       	int result = 0;
        result = first + (second * 2);
        return result;
    }
    public int solution(int[] scoville, int K) {
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int s: scoville) {
            minHeap.add(s);
        }
       
        int count = 0;
       
        while (minHeap.size() > 1 && minHeap.peek() < K) {
            int min1 = minHeap.poll();
            int min2 = minHeap.poll();

            int mix = calcScoville(min1, min2);
            minHeap.add(mix);
            // System.out.println(min1);
            // System.out.println(min2);
            count++;
        }
        
        return minHeap.peek() >= K ? count : -1;
    }
}