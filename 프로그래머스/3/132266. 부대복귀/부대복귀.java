import java.util.*;

class Solution {
    int n;
    List<List<Integer>> graph;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        this.n = n; 
        
       	graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] road: roads) {
            int from = road[0];
            int to = road[1];
            
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        
        int[] dists = bfs(destination); // 역방향으로 거리 추적 
        
        int len = sources.length;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
           	result[i] = dists[sources[i]]; 
        }
        return result;
    }
    
    private int[] bfs(int start) {
        int[] dist = new int[n + 1]; 
        Arrays.fill(dist, -1);
        dist[start] = 0;
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
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