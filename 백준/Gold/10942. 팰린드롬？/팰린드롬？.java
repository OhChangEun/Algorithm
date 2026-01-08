import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];
        String[] parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i + 1] = Integer.parseInt(parts[i]);
        }

        // i번째부터 j까지가 팰린드롬인지 기록하는 dp
        boolean[][] dp = new boolean[n + 1][n + 1];

        // 부분 배열의 길이가 1일 때
        for (int i = 1; i <= n; i++) {
            dp[i][i] = true;
        }
        // 부분 배열의 길이가 2일 때
        for (int i = 1; i <= n - 1; i++) {
            if (arr[i] == arr[i + 1])
                dp[i][i + 1] = true;
        }
        // 부분 배열의 길이가 3일 때
        for (int len = 3; len <= n; len++) {
            for (int start = 1; start <= n - len + 1; start++) {
                int end = start + len - 1;

                if (arr[start] == arr[end] && dp[start + 1][end - 1]) {
                    dp[start][end] = true;
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            parts = br.readLine().split(" ");
            int s = Integer.parseInt(parts[0]);
            int e = Integer.parseInt(parts[1]);

            sb.append(dp[s][e] ? 1 : 0).append("\n");
        }
        System.out.println(sb.toString());
    }
}