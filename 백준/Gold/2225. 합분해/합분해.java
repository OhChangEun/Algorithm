import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static long[][] dp;

    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");
        n = Integer.parseInt(parts[0]);
        k = Integer.parseInt(parts[1]);

        dp = new long[n + 1][k + 1];

        Arrays.fill(dp[0], 1);

        for (int i = 0; i <= n; i++) {
            dp[i][1] = 1;
        }

        for (int len = 1; len <= n; len++) {
            for (int val = 1; val <= k; val++) {

                long prevSum = 0;
                for (int prev = 1; prev <= val; prev++) {
                    prevSum = (prevSum + dp[len - 1][prev]) % MOD;
                }

                dp[len][val] = prevSum;
            }
        }
        
        System.out.println(dp[n][k] % MOD);
    }
}
