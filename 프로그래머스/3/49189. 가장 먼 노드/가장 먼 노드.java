import java.util.*;

class Solution {
    int n;
    List<List<Integer>> graph;
    public int solution(int n, int[][] edge) {
        this.n = n; 
        
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
      
        for (int[] e: edge) {
            int u = e[0];
            int v = e[1];
            
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        int start = 1; 
        int[] dists = bfs(start); 
        
        int maxDist = 0;
        for (int dist: dists) {
            maxDist = Math.max(maxDist, dist);
        }
        
        int cnt = 0; 
        for (int dist: dists) {
            if (dist == maxDist) cnt++;
        }
        
        return cnt;
    }
    
    private int[] bfs(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[start] = 0; 
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            for (int next: graph.get(curr)) {
                if (dist[next] == -1) {
                    dist[next] = dist[curr] + 1; 
                    queue.offer(next);
                }
            }
        }
        
        return dist;
    }
}