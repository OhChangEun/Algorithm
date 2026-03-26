import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] dp;

    static final int MOD = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());


        dp = new int[n + 1][10];
        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= 9; i++) {
                int prevSum = 0;
                for (int prev = i; prev <= 9; prev++) {
                    prevSum = (prevSum + dp[len - 1][prev]) % MOD;
                }
                dp[len][i] = prevSum;
            }
        }

        int result = 0;
        for (int i = 0; i <= 9; i++) {
            result += dp[n][i];
        }

        System.out.println(result % MOD);
    }
}