import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 200_000_000;
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
        int N = Integer.parseInt(parts[0]);
        int E = Integer.parseInt(parts[1]);

        List<List<Node>> graph = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i=0; i<E; i++) {
            parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int w = Integer.parseInt(parts[2]);
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }
        // System.out.println(graph);
        parts = br.readLine().split(" ");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);

        int[] distStart = dijkstra(N, graph, 1);
        int[] distA = dijkstra(N, graph, a);
        int[] distB = dijkstra(N, graph, b);

        // 1 > a > b > N
        int pathA = distStart[a] + distA[b] + distB[N];
        // 1 > b > a > N
        int pathB = distStart[b] + distB[a] + distA[N];

        int result = Math.min(pathA, pathB);
        if (result >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(result);
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