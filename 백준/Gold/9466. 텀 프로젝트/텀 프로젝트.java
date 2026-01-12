import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int t, n;
    static List<List<Integer>> graph;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());

            indegree = new int[n + 1];
            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            String[] parts = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                int u = i + 1;
                int v = Integer.parseInt(parts[i]);

                graph.get(u).add(v);
                indegree[v]++;
            }

            int result = topologicalSort();
            sb.append(result).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;

            for (int next: graph.get(curr)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return count;
    }
}