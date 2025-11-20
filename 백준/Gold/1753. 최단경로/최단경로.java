import java.io.*;
import java.util.*;

public class Main {
    public static final int INF = 200_000;
    public static class Node implements Comparable<Node> {
        int vertex;
        int cost;
        public Node (int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        int V = Integer.parseInt(parts[0]); // 정점의 개수
        int E = Integer.parseInt(parts[1]); // 간선의 개수
        int K = Integer.parseInt(br.readLine()); // 시작 정점

        List<List<Node>> graph = new ArrayList<>();
        for (int i=0; i<=V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i=0; i<E; i++) {
            parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int w = Integer.parseInt(parts[2]);

            graph.get(u).add(new Node(v, w));
        }

        int[] result = dijkstra(V, graph, K);
        for (int i=1; i<=V; i++) {
            int num = result[i];
            if (num >= INF) System.out.println("INF");
            else System.out.println(num);
        }
    }
    public static int[] dijkstra(int n, List<List<Node>> graph, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int currNode = curr.vertex;
            int currCost = curr.cost;

            if (currCost > dist[curr.vertex]) continue;
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
