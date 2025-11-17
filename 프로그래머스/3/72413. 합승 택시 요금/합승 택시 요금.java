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
    public int[] dijstra(int n, List<List<Node>> graph, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
      	dist[start] = 0; 
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
   		pq.offer(new Node(start, 0));
      	while(!pq.isEmpty()) {
            Node curr = pq.poll();
    		int now = curr.vertex;
            // 현재 비용이 현재까지 알려진 비용보다 크다면 확인해볼 필요 x
            if (curr.cost > dist[now]) continue; 
            
            // 다음 노드들 중에서 
            for (Node next: graph.get(now)) {
           		int newDist = dist[now] + next.cost;
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
        
        int[] distS = dijstra(n, graph, s);
        int[] distA = dijstra(n, graph, a);
        int[] distB = dijstra(n, graph, b);
        
        int result = Integer.MAX_VALUE;
        for (int k=1; k<=n; k++) {
           	int total = distS[k] + distA[k] + distB[k]; 
            result = Math.min(result, total);
        }
        
        return result;
    }
}