import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, r, q;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        n = Integer.parseInt(parts[0]);
        r = Integer.parseInt(parts[1]);
        q = Integer.parseInt(parts[2]);

        graph = new ArrayList<>();
        for (int i = 0; i <= n ; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dp = new int[n + 1];
        visited = new boolean[n + 1];
        dfs(r);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int query = Integer.parseInt(br.readLine());
            sb.append(dp[query]).append("\n");
        }
        
        System.out.println(sb.toString());
    }

    static int[] dp;
    static boolean[] visited;
    private static void dfs(int curr) {
        visited[curr] = true;
        dp[curr] = 1;

        for (int next: graph.get(curr)) {
            if (!visited[next]) {
                dfs(next);

                dp[curr] += dp[next];
            }
        }
    }
}
