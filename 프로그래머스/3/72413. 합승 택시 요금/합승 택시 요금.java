import java.util.*;

class Solution {
    List<List<Node>> graph; 
    int n;
    
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

    public int solution(int n, int s, int a, int b, int[][] fares) {
        this.n = n; 
        
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
       
        for (int[] fare: fares) {
            int u = fare[0];
            int v = fare[1];
            int cost = fare[2];
            
            graph.get(u).add(new Node(v, cost));
            graph.get(v).add(new Node(u, cost));
        }
      
        int minCost = Integer.MAX_VALUE;
        for (int start = 1; start <= n; start++) {
            int cost[] = dijkstra(start);
            int totalCost = cost[s] + cost[a] + cost[b];
            
            minCost = Math.min(minCost, totalCost);
        }
        
        return minCost;
    }
    
    private int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        
        while (!pq.isEmpty()) {
            Node curr = pq.poll(); 
            int currNode = curr.vertex;
            int currCost = curr.cost;
           
            if (currCost > dist[currNode]) continue;
            
            for (Node next: graph.get(currNode)) {
                int newCost = dist[currNode] + next.cost;
                if (newCost < dist[next.vertex]) {
                    dist[next.vertex] = newCost; 
                    pq.offer(new Node(next.vertex, newCost));
                }
            }
        }
        
        return dist;
    }
}