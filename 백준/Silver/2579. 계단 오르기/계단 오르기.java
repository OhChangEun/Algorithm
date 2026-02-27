import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i + 1] = Integer.parseInt(br.readLine());
        }
        
        if (n == 1) {
            System.out.println(arr[1]);
            return;
        }
        
        dp = new int[n + 1];
        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];

        for (int i = 3; i <= n; i++) {
            dp[i] = arr[i] + Math.max(dp[i - 2], arr[i - 1] + dp[i - 3]);
        }

        System.out.println(dp[n]);
    }
}