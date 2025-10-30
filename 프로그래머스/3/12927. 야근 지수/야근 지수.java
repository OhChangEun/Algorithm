import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int work: works) {
            maxHeap.add(work);
        }
        
       	for (int i=0; i<n; i++) {
            int max = maxHeap.poll();
            if (max > 1) {
               	maxHeap.add(max - 1);
            }
            if (maxHeap.isEmpty()) return 0;
        } 
        long result = 0;
   		while (!maxHeap.isEmpty()) {
       		int num = maxHeap.poll();
            result += num * num;
        } 
        return result;
    }
}