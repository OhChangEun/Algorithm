import java.util.*;

class Solution {
    List<List<Integer>> graph; 
    boolean[] visited;

    public int solution(int n, int[][] computers) {
        
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    graph.get(i).add(j);
                }
            }
        }
        
        int networkCnt = 0;
        
        visited = new boolean[n];
        for (int start = 0; start < n; start++) {
            if (!visited[start]) {
                bfs(n, start);
                networkCnt++;   
            }
        }
        
        // print(graph);
        
        return networkCnt;
    }
    
    private void bfs(int n, int start) {
        visited[start] = true; 
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
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
    
    private void print(Object o) {
        System.out.println(o);
    }
}