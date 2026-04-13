import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] cost;
    static int[][] dp;

    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(parts[j]);
            }
        }

        dp = new int[n][1 << n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(tps(0, 1));
    }

    public static int tps(int now, int mask) {
        if (mask == (1 << n) - 1) {
            if (cost[now][0] == 0) return INF;
            return cost[now][0];
        }

        if (dp[now][mask] != -1) {
            return dp[now][mask];
        }

        dp[now][mask] = INF;

        for (int next = 0; next < n; next++) {
            if ((mask & (1 << next)) != 0) continue;

            if (cost[now][next] == 0) continue;

            int sum = cost[now][next] + tps(next, mask | (1 << next));

            dp[now][mask] = Math.min(dp[now][mask], sum);
        }

        return dp[now][mask];
    }
}