import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int vertex, cost;

        public Node (int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

    static int n, m;
    static List<List<Node>> graph;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");

        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            parts = br.readLine().split(" ");

            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int cost = Integer.parseInt(parts[2]);

            graph.get(u).add(new Node(v, cost));
            graph.get(v).add(new Node(u, cost));
        }

        sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            parts = br.readLine().split(" ");

            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);

            bfs(start, end);
        }

        System.out.println(sb.toString());
    }

    static void bfs(int start, int end) {
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.vertex == end) {
                sb.append(curr.cost).append("\n");
                return;
            }

            for (Node next: graph.get(curr.vertex)) {
                if (!visited[next.vertex]) {
                    visited[next.vertex] = true;
                    queue.offer(new Node(next.vertex, curr.cost + next.cost));
                }
            }
        }
    }
}