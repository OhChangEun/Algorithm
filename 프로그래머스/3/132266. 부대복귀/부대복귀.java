import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        // System.out.println(graph);
        for (int[] road: roads) {
            int u = road[0];
            int v = road[1];
            graph.get(u).add(v);    
            graph.get(v).add(u);    
        }
       
        // 도착점 기준으로 시작점
        int[] dist = bfs(n, graph, sources, destination); 
        int[] result = new int[sources.length];
        for (int i=0; i<sources.length; i++) {
            result[i] = dist[sources[i]];
        }
        return result;
    }
    public int[] bfs(int n, List<List<Integer>> graph, int[] sources, int start) {
        int target = sources[0];  
        int[] dist = new int[n + 1]; 
        Arrays.fill(dist, -1);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        dist[start] = 0; 
        
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