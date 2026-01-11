import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] indegree;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");
        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);

        indegree = new int[n + 1];

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            parts = br.readLine().split(" ");

            int singerNum = Integer.parseInt(parts[0]);
            for (int j = 1; j < singerNum; j++) {
                int curr = Integer.parseInt(parts[j]);
                int next = Integer.parseInt(parts[j + 1]);
                graph.get(curr).add(next);
                indegree[next]++;
            }
        }

        List<Integer> result = topologicalSort();

        if (result.size() != n) {
            System.out.println(0);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int singer: result) {
                sb.append(singer).append("\n");
            }

            System.out.println(sb.toString());
        }
    }

    private static List<Integer> topologicalSort() {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result.add(curr);

            for (int next: graph.get(curr)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return result;
    }
}