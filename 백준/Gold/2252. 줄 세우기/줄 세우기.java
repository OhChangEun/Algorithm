import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[1]);
            int v = Integer.parseInt(parts[0]);

            parent[v] = u;
            graph.get(u).add(v);
        }


        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!visited[i] && parent[i] == i) {
                // System.out.println("i: " + i);
                dfs(i);
            }
        }
    }

    private static void dfs(int curr) {
        visited[curr] = true;

        for (int next: graph.get(curr)) {
            if (!visited[next]) {
                dfs(next);
            }
        }

        System.out.print(curr + " ");
    }
}