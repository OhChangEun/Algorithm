import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, m, x;
    private static List<List<Node>> graph;

    static class Node implements Comparable<Node> {
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);
        x = Integer.parseInt(parts[2]);

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int w = Integer.parseInt(parts[2]);

            graph.get(u).add(new Node(v, w));
        }

        int[] distX = dijkstra(x);

        int maxDist = 0;
        for (int i = 1; i <= n; i++) {
            int[] dist = dijkstra(i);
            int total = dist[x] + distX[i];
            maxDist = Math.max(maxDist, total);
        }

        System.out.println(maxDist);
    }

    private static int[] dijkstra(int start) {
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
                int newDist = dist[currNode] + next.cost;
                if (newDist < dist[next.vertex]) {
                    dist[next.vertex] = newDist;
                    pq.offer(new Node(next.vertex, newDist));
                }
            }
        }

        return dist;
    }
}