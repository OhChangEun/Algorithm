import java.util.*;
import java.io.*;

public class Main {
    public static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i=0; i<=N; i++) {
            parent[i] = i;
        }
        int[][] costs = new int[M][3];
        for (int i=0; i<M; i++) {
            String[] parts = br.readLine().split(" ");
            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);
            int cost = Integer.parseInt(parts[2]);

            costs[i][0] = start;
            costs[i][1] = end;
            costs[i][2] = cost;
        }

        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        int edgeCount = 0;
        int totalCost = 0;
        for (int[] cost: costs) {
            int start = cost[0];
            int end = cost[1];
            int edgeCost = cost[2];
            // System.out.println(start + " " + end + " " + edgeCost);

            if (find(start) != find(end)) {
                union(start, end);
                totalCost += edgeCost;
                edgeCount++;

                if (edgeCount == N - 1) break;
            }
        }
        System.out.println(totalCost);
    }

    public static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA < rootB) parent[rootB] = rootA;
        else parent[rootA] = rootB;
    }
}
