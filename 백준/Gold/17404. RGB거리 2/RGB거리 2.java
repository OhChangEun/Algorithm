import java.io.*;

public class Main {
    static final int INF = 1000 * 1000 + 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] costs = new int[n + 1][3];
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            costs[i][0] = Integer.parseInt(parts[0]);
            costs[i][1] = Integer.parseInt(parts[1]);
            costs[i][2] = Integer.parseInt(parts[2]);
        }

        int[][] dp = new int[n][3];
        int result = INF;
        for (int startColor = 0; startColor < 3; startColor++) {
            for (int i = 0; i < 3; i++) {
                if (i == startColor) {
                    dp[0][i] = costs[0][i];
                } else {
                    dp[0][i] = INF;
                }
            }

            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
            }

            for (int endColor = 0; endColor < 3; endColor++) {
                if (startColor != endColor) {
                    result = Math.min(result, dp[n - 1][endColor]);
                }
            }
        }

        System.out.println(result);
    }
}