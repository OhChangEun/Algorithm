import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] dp;
    static final int NUM = 12;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        dp = new int[NUM];

        dp[0] = 1;
        for (int i = 1; i < NUM; i++) {
            dp[i] += dp[i - 1];
            if (i >= 2) dp[i] += dp[i - 2];
            if (i >= 3) dp[i] += dp[i - 3];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int idx = Integer.parseInt(br.readLine());
            sb.append(dp[idx]).append("\n");
        }

        System.out.println(sb.toString());
    }
}