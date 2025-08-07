import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
      	// Queue<Integer> lose = new LinkedList<>();
    
        int loseCount = 0;
      
        for (int i=0; i<B.length; i++) {
            minHeap.add(B[i]);
        }
        
        int count = 0;
        Arrays.sort(A);
        Arrays.sort(B);
       	for (int i=0; i<A.length; i++) {	
           	while (!minHeap.isEmpty()) {
            	int minB = minHeap.poll();
                if (A[i] < minB) {
                	count++;
                    break;
                }
            }     
        } 
        
        return count;
    }
}