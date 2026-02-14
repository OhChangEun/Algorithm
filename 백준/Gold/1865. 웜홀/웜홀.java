import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int from, to, cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static List<Node> graph;
    static int n;
    static int m;
    static int w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (tc-- > 0) {
            String[] parts = br.readLine().split(" ");
            n = Integer.parseInt(parts[0]);
            m = Integer.parseInt(parts[1]);
            w = Integer.parseInt(parts[2]);

            graph = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                parts = br.readLine().split(" ");

                int s = Integer.parseInt(parts[0]);
                int e = Integer.parseInt(parts[1]);
                int t = Integer.parseInt(parts[2]);

                graph.add(new Node(s, e, t));
                graph.add(new Node(e, s, t));
            }

            for (int i = 0; i < w; i++) {
                parts = br.readLine().split(" ");

                int s = Integer.parseInt(parts[0]);
                int e = Integer.parseInt(parts[1]);
                int t = Integer.parseInt(parts[2]);

                graph.add(new Node(s, e, -t));
            }

            if (hasNegativeCycle()) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static boolean hasNegativeCycle() {
        int[] dists = new int[n + 1];
        Arrays.fill(dists, 0);

        for (int i = 0; i < n - 1; i++) {
            for (Node node: graph) {
                if (dists[node.from] + node.cost < dists[node.to]) {
                    dists[node.to] = dists[node.from] + node.cost;
                }
            }
        }

        for (Node node: graph) {
            if (dists[node.from] + node.cost < dists[node.to]) {
                return true;
            }
        }
        return false;
    }
}