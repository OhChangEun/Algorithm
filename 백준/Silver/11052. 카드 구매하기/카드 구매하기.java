import java.io.*;

public class Main {
    static int n;
    static int[] dp;

    static int[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        cards = new int[n + 1];
        String[] parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            cards[i + 1] = Integer.parseInt(parts[i]);
        }

        dp = new int[n + 1];
        for (int c = 1; c <= n; c++) {
            int card = cards[c];

            for (int i = c; i <= n; i++) {
                dp[i] = Math.max(dp[i], dp[i - c] + card);
            }
        }

        System.out.println(dp[n]);

    }
}