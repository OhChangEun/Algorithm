import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        
        // [배열 길이][마지막으로 사용한 숫자][방문 여부 - mask]
        long[][][] dp = new long[n + 1][10][1 << 10];

        // 1~9로 시작하는 숫자 1로 초기화
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int len = 1; len < n; len++) {
            for (int digit = 0; digit <= 9; digit++) {
                for (int mask = 0; mask < (1 << 10); mask++) {

                    if (dp[len][digit][mask] == 0) continue;

                    if (digit >= 1) {
                        int nextMask = mask | 1 << (digit - 1); // 방문 처리
                        dp[len + 1][digit - 1][nextMask] += dp[len][digit][mask];
                        dp[len + 1][digit - 1][nextMask] %= MOD;
                    }

                    if (digit <= 8) {
                        int nextMask = mask | 1 << (digit + 1); // 방문 처리
                        dp[len + 1][digit + 1][nextMask] += dp[len][digit][mask];
                        dp[len + 1][digit + 1][nextMask] %= MOD;
                    }
                }
            }
        }

        long totalCnt = 0;
        int fullMask = (1 << 10) - 1;

        for (int digit = 0; digit <= 9; digit++) {
            totalCnt += dp[n][digit][fullMask];
            totalCnt %= MOD;
        }

        System.out.println(totalCnt);
    }
}
