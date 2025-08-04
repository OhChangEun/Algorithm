import java.util.*;

class Solution {
    public void bfs(int start, int[][] computers, boolean[] visited) {
      	Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        
        while (!q.isEmpty()) {
            int curr = q.poll();
            
            for (int i=0; i<computers.length; i++) {
                if (curr != i && computers[curr][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] visited = new boolean[n];
        
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                bfs(i, computers, visited);
                answer++;
            }
        }
        
        return answer;
    }
}