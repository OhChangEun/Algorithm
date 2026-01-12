import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] orders;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");

        n = parts.length - 1; // 마지막 0 제외

        // 1 2 2 4
        orders = new int[n];
        for (int i = 0; i < n; i++) {
            orders[i] = Integer.parseInt(parts[i]);
        }

        int result = playDDR();
        System.out.println(result);
    }

    private static int cost(int from, int to) {
        if (from == to) return 1;
        else if (from == 0) return 2;
        else if (Math.abs(to - from) == 2) return 4;
        else return 3;
    }

    private static int playDDR() {
        int[][][] dp = new int[n + 1][5][5];
        for (int i = 0; i <= n; i++) {
            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    dp[i][l][r] = Integer.MAX_VALUE;
                }
            }
        }

        dp[0][0][0] = 0;

        for (int i = 0; i < n; i++) {
            int next = orders[i];
            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    if (dp[i][l][r] == Integer.MAX_VALUE) continue;

                    dp[i + 1][next][r] = Math.min(dp[i + 1][next][r], dp[i][l][r] + cost(l, next));
                    dp[i + 1][l][next] = Math.min(dp[i + 1][l][next], dp[i][l][r] + cost(r, next));
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int l = 0; l < 5; l++) {
            for (int r = 0; r < 5; r++) {
                result = Math.min(result, dp[n][l][r]);
            }
        }

        return result;
    }
}