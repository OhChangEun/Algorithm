import java.util.*;

class Solution {
    class Node implements Comparable<Node> {
        int vertex; 
        int cost; 
        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost; 
        }
    }
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] path: paths) {
            int u = path[0];
            int v = path[1];
            int w = path[2];
            
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }
     
        boolean[] isSummit = new boolean[n + 1];
        for (int s: summits) {
            isSummit[s] = true;
        }
        
        int[] dist = dijkstra(n, graph, gates, isSummit);
        
        int summitNum = 0; 
        int minIntensity = Integer.MAX_VALUE;
        
        Arrays.sort(summits); 
        for (int s: summits) {
            if (dist[s] < minIntensity) {
                minIntensity = dist[s];
                summitNum = s; 
            }
        }
        
        return new int[] { summitNum, minIntensity };
    }
    
    private int[] dijkstra(int n, List<List<Node>> graph, int[] gates, boolean[] isSummit) {
        int[] dist = new int[n + 1]; 
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int g: gates) {
            dist[g] = 0;
            pq.offer(new Node(g, 0));
        }
        
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int currNode = curr.vertex; 
            int currCost = curr.cost;
            
            if (currCost > dist[currNode]) continue; 
            if (isSummit[currNode]) continue; 
            
            for (Node next: graph.get(currNode)) {
                int newCost = Math.max(dist[currNode], next.cost);
                if (newCost < dist[next.vertex]) {
                    dist[next.vertex] = newCost; 
                    pq.offer(new Node(next.vertex, newCost));
                }
            }
        }
        
        return dist; 
    }
}