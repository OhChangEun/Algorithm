import java.util.*;

class Solution {
    boolean[] visited; 
    int count = 0;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n]; 
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                bfs(i, n, computers); 
                count++;
            }
        }
        return count;
    }
    private void bfs(int num, int n, int[][] computers) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(num);
        visited[num] = true; 
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
        
            for (int i=0; i<n; i++) {
                if (i!=curr && computers[curr][i] == 1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                } 
            }
        }
    }
}