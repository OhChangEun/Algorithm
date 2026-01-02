import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int m;
    static int r;

    static int[] items;
    static List<List<Node>> graph;

    static class Node implements Comparable<Node>{
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
        r = Integer.parseInt(parts[2]);

        items = new int[n + 1];
        parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            items[i + 1] = Integer.parseInt(parts[i]);
        }
        /*
        for (int num: items) {
            System.out.print(num + " ");
        }*/

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < r; i++) {
            parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int w = Integer.parseInt(parts[2]);

            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        /*
        int[] dist = dijkstra(1, n);
        for (int num: dist) {
            System.out.print(num + " ");
        }
        System.out.println();
         */
        int maxCost = 0;
        for (int i = 1; i <= n; i++) { // 각 경로별 최단 거리
            int totalCost = 0;
            int[] dist = dijkstra(i, n);
            for (int j = 1; j <= n; j++) {
                if (dist[j] <= m) { // 갈 수 있는 거리
                    totalCost += items[j]; // 갈 수 있는 거리의 합
                }
            }

            maxCost = Math.max(maxCost, totalCost);
        }

        System.out.println(maxCost);
    }

    private static int[] dijkstra(int start, int n) {
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