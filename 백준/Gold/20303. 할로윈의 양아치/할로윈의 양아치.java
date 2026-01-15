import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[] candies;
    static List<List<Integer>> graph;
    static boolean[] visited;

    static List<Group> groupList;
    static int[] dp;

    static class Group {
        int count;
        int candy;

        public Group(int count, int candy) {
            this.count = count;
            this.candy = candy;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");
        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);
        k = Integer.parseInt(parts[2]);

        candies = new int[n + 1];
        parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            candies[i + 1] = Integer.parseInt(parts[i]);
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        visited = new boolean[n + 1];
        groupList = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            if (!visited[i]) {
                Group g = bfs(i);
                groupList.add(g);
            }
        }

        dp = new int[k];
        for (Group g: groupList) {
            int count = g.count;
            int candy = g.candy;

            for (int i = k - 1; i >= count; i--) {
                dp[i] = Math.max(dp[i], dp[i - count] + candy);
            }
        }

        int answer = 0;
        for (int num: dp) {
            answer = Math.max(answer, num);
        }

        System.out.println(answer);
    }

    private static Group bfs(int start) {
        visited[start] = true;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        int count = 0;
        int candy = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;
            candy += candies[curr];

            for (int next: graph.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        return new Group(count, candy);
    }
}