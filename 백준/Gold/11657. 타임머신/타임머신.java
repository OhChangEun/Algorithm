import java.io.*;
import java.util.*;

public class Main {
    static final long INF = Long.MAX_VALUE;

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String[] parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            parts = br.readLine().split(" ");
            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            int cost = Integer.parseInt(parts[2]);

            edges.add(new Edge(from, to, cost));
        }

        int start = 1;
        long[] dists = bellmanFord(n, edges, start);

        if (dists == null) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < dists.length; i++) {
                long dist = dists[i];
                sb.append(dist == INF ? -1 : dist).append("\n");
            }
            System.out.println(sb.toString());
        }
    }

    private static long[] bellmanFord(int n, List<Edge> edges, int start) {
        long[] dists = new long[n + 1];
        Arrays.fill(dists, INF);
        dists[start] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (Edge edge: edges) {
                if (dists[edge.from] == INF) continue;

                if (dists[edge. to] > dists[edge.from] + edge.cost) {
                    dists[edge.to] = dists[edge.from] + edge.cost;
                }
            }
        }

        for (Edge edge: edges) {
            if (dists[edge.from] == INF) continue;

            if (dists[edge.to] > dists[edge.from] + edge.cost) {
                return null;
            }
        }

        return dists;
    }
}