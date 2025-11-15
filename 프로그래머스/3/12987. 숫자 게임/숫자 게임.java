import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
       	PriorityQueue<Integer> pq = new PriorityQueue<>();
      
        for (int b: B) {
            pq.add(b);
        }
        
        int count = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        for (int i=0; i<A.length; i++) {
       		while (!pq.isEmpty()) {
                int minB = pq.poll();
                if (A[i] < minB) {
                    count++;
                    break;
                }
            }     
        }
        
        return count;
    }
}