import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long[][] dp;

    static final int MOD = 9901;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        dp = new long[n + 1][3];

        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= n; i++) {
            int prev = i - 1;

            dp[i][0] = (dp[prev][0] + dp[prev][1] + dp[prev][2]) % MOD; 
            dp[i][1] = (dp[prev][0] + dp[prev][2]) % MOD; // 왼쪽
            dp[i][2] = (dp[prev][0] + dp[prev][1]) % MOD; // 오른쪽
        }

        long result = 0;
        for (int i = 0; i < 3; i++) {
            result = (result + dp[n][i]) % MOD;
        }
        System.out.println(result);

    }
}
