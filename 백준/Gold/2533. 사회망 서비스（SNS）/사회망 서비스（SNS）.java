import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<List<Integer>> graph;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            String[] parts = br.readLine().split(" ");
            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        int root = 1;
        dfs(root);

        System.out.println(Math.min(dp[root][0], dp[root][1]));
    }

    private static void dfs(int curr) {
        visited[curr] = true;

        dp[curr][0] = 0;
        dp[curr][1] = 1;

        for (int next: graph.get(curr)) {
            if (!visited[next]) {
                dfs(next);

                dp[curr][0] += dp[next][1];
                dp[curr][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }
}