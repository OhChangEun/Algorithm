import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Promotion {
        int cost;
        int profit;

        public Promotion(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        int c = Integer.parseInt(parts[0]);
        int n = Integer.parseInt(parts[1]);

        int[] dp = new int[c + 100];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        Promotion[] proms = new Promotion[n];
        for (int i = 0; i < n; i++) {
            parts = br.readLine().split(" ");
            int cost = Integer.parseInt(parts[0]);
            int profit = Integer.parseInt(parts[1]);

            proms[i] = new Promotion(cost, profit);
        }

        for (int i = 1; i < c + 100; i++) {
            for (Promotion p: proms) {
                if (i - p.profit >= 0 && dp[i - p.profit] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - p.profit] + p.cost);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = c; i < c + 100; i++) {
            result = Math.min(result, dp[i]);
        }

        System.out.println(result);
    }
}