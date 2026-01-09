import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k;
    static int[] buildings;
    static List<List<Integer>> graph;
    static int[] indegree;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            String[] parts = br.readLine().split(" ");
            n = Integer.parseInt(parts[0]);
            k = Integer.parseInt(parts[1]);
            buildings = new int[n + 1]; // 1-based

            parts = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                buildings[i + 1] = Integer.parseInt(parts[i]);
            }

            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            indegree = new int[n + 1];
            for (int i = 0; i < k; i++) {
                parts = br.readLine().split(" ");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);

                graph.get(u).add(v);
                indegree[v]++;
            }

            int w = Integer.parseInt(br.readLine());
            // System.out.println("w: " + w);

            result = 0;
            int result = bfs(w);
            sb.append(result + "\n");
        }

        System.out.println(sb.toString());
    }

    static private int bfs(int end) {
        int[] dp = new int[n + 1]; // 해당 건물 완공 시간

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                dp[i] = buildings[i];
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int next: graph.get(curr)) {
                dp[next] = Math.max(dp[next], dp[curr] + buildings[next]);
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return dp[end];
    }
}