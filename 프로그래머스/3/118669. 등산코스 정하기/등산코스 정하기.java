import java.util.*;

class Solution {
    class Node implements Comparable<Node> {
        int vertex; 
        int cost; 
        
        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
        
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    } 
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) graph.add(new ArrayList<>());
        
        for (int[] p: paths) {
            int a = p[0];
            int b = p[1];
            int w = p[2];
            graph.get(a).add(new Node(b, w));
            graph.get(b).add(new Node(a, w));
        }
        
        boolean[] isSummit = new boolean[n + 1];
        for (int s: summits) isSummit[s] = true;
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for (int gate: gates) {
            dist[gate] = 0;
            pq.offer(new Node(gate, 0));
        }
        
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int now = curr.vertex;
            int cost = curr.cost;
            
            if (dist[now] < cost) continue;
            if (isSummit[now]) continue;
            
            for (Node next: graph.get(now)) {
                int newCost = Math.max(dist[now], next.cost);
                if (dist[next.vertex] > newCost) {
                    dist[next.vertex] = newCost;
                    pq.offer(new Node(next.vertex, newCost));
                }
            }
        }
        
        Arrays.sort(summits);
        int minIntensity = Integer.MAX_VALUE;
        int answerSummit = 0; 
        
        for (int s: summits) {
            if (dist[s] < minIntensity) {
                minIntensity = dist[s];
                answerSummit = s;
            }
        }
        return new int[] {answerSummit, minIntensity}; 
    }
}