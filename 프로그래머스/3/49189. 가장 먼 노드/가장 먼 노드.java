import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
       
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        // System.out.println(graph);
        for (int[] e: edge) {
            int u = e[0];
            int v = e[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        // System.out.println(graph);
       
        int start = 1;
        int[] dist = bfs(n, start, graph);

        int max = 0;
        for (int d: dist) {
            max = Math.max(d, max);
        }
        
        int count = 0;
        for (int d: dist) {
            if (d == max) count++;
        }
       
        return count;
    }
    public int[] bfs(int n, int start, List<List<Integer>> graph) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1); 
        
        Queue<Integer> queue = new LinkedList<>();
        dist[start] = 0;
        queue.offer(start);
        
        while(!queue.isEmpty()) {
            int curr = queue.poll();
        
            // System.out.println(curr);
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