import java.util.*;

class Solution {
    List<List<Integer>> graph; 
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        
        int len = computers.length;
        graph = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                // 자기 자신을 제외하고 
                if (i != j && computers[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        
        int networkCnt = 0;
        visited = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                visited[i] = true; 
                bfs(i);
                networkCnt++;
            }
        }
        
        return networkCnt;
    }
    
    private void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start); 
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            for (int next: graph.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true; 
                    queue.offer(next);
                }
            }
        }
    }
}