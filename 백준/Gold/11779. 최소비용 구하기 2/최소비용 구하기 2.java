import java.io.*;
import java.util.*;

public class Main {
    public static class Node implements Comparable<Node> {
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

    public static int[] dijkstra(int n, List<List<Node>> graph, int start, int[] parent) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            int currNode = curr.vertex;
            int currCost = curr.cost;

            if (currCost > dist[currNode]) continue;
            for (Node next: graph.get(currNode)) {
                int newCost = dist[currNode] + next.cost;
                if (newCost < dist[next.vertex]) {
                    dist[next.vertex] = newCost;
                    parent[next.vertex] = currNode;
                    pq.offer(new Node(next.vertex, newCost));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<Node>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i=0; i<m; i++) {
            String[] parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int w = Integer.parseInt(parts[2]);
            graph.get(u).add(new Node(v, w));
        }

        String[] parts = br.readLine().split(" ");
        int start = Integer.parseInt(parts[0]);
        int end = Integer.parseInt(parts[1]);

        int[] parent = new int[n + 1];
        int[] dist = dijkstra(n, graph, start, parent);

        // 경로 추적
        List<Integer> path = new ArrayList<>();
        int curr = end;
        while (curr != 0) {
            path.add(curr);
            curr = parent[curr];
        }
        Collections.reverse(path);

        // 출력문
        System.out.println(dist[end]);
        System.out.println(path.size());
        for (int city: path) {
            System.out.print(city + " ");
        }
    }
}
