import java.util.*;

class Solution {
    class Node implements Comparable<Node> {
        int vertex; 
        int cost; 
        public Node (int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }
   
    public int[] dijkstra(int n, List<List<Node>> graph, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.cost > dist[curr.vertex]) continue;
            for (Node next: graph.get(curr.vertex)) {
                int newDist = dist[curr.vertex] + next.cost; 
                if (newDist < dist[next.vertex]) {
                    dist[next.vertex] = newDist; 
                    pq.offer(new Node(next.vertex, newDist));
                }
            }
        }
   
        return dist;
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] fare: fares) {
            int u = fare[0];
            int v = fare[1];
            int w = fare[2];
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        } 
        // System.out.println(graph);
 
        int[] distS = dijkstra(n, graph, s);
        int[] distA = dijkstra(n, graph, a);
        int[] distB = dijkstra(n, graph, b);
        
        // 3개의 거리합의 최소가 되는 지점 > k를 찾아야 함
        int minCost = Integer.MAX_VALUE;
        for (int k=1; k<=n; k++) {
            int total = distS[k] + distA[k] + distB[k];
            minCost = Math.min(total, minCost);
        }
        
        return minCost;
    }
}