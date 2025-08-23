import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        String[] parts = br.readLine().split(" ");
        for (int i=0; i<N; i++) {
            A[i] = Integer.parseInt(parts[i]);
        }

        int max = 0;
        int[] dp = new int[N];
        for (int i=0; i<N; i++) {
            dp[i] = 1;
            for (int j=0; j<i; j++) {
                if (A[j] < A[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}