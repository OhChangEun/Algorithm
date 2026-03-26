import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] dp;
    static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");
        n = Integer.parseInt(parts[0]);
        k = Integer.parseInt(parts[1]);

        coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[k + 1];

        dp[0] = 1;
        for (int coin: coins) {
            for (int i = coin; i <= k; i++) {
                dp[i] += dp[i - coin];
            }
        }

        System.out.println(dp[k]);
    }
}