import java.util.*;

class Solution {
    public void bfs(int start, boolean[] visited, int[][] computers) {
       	Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            for (int i=0; i<computers.length; i++) {
                if (i!=curr && computers[curr][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i); 
                }
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
       
        boolean[] visited = new boolean[n];
       
        int num = 0;
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                bfs(i, visited, computers);
           		num++; 
            }
        }
        
        return num;
    }
}