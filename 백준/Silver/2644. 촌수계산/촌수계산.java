import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static List<List<Integer>> graph;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        String[] parts = br.readLine().split(" ");

        int man1 = Integer.parseInt(parts[0]);
        int man2 = Integer.parseInt(parts[1]);

        m = Integer.parseInt(br.readLine());
        indegree = new int[n + 1];

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            parts = br.readLine().split(" ");
            int parent = Integer.parseInt(parts[0]);
            int child = Integer.parseInt(parts[1]);

            graph.get(parent).add(child);
            graph.get(child).add(parent);
        }

        System.out.println(calc(man1, man2));
    }

    static int calc(int start, int target) {
        int[] dists = new int[n + 1];
        Arrays.fill(dists, -1);
        dists[start] = 0;

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (curr == target) {
                return dists[curr];
            }

            for (int next: graph.get(curr)) {
                if (dists[next] == -1) {
                    dists[next] = dists[curr] + 1;
                    queue.offer(next);
                }
            }
        }

        return -1;
    }
}