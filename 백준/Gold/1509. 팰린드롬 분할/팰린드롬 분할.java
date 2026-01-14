import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static String input;
    static int[] dp;
    static boolean[][] isPalindrome;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        n = input.length();

        isPalindrome = new boolean[n][n];
        for (int center = 0; center < n; center++) {
            getPalindrome(center, center);
            getPalindrome(center, center + 1);
        }

        dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (isPalindrome[j][i]) {
                    if (j == 0) dp[i] = 1;
                    else
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }

        System.out.println(dp[n - 1]);
    }

    private static void getPalindrome(int left, int right) {
        while (left >= 0 && right < n && input.charAt(left) == input.charAt(right)) {
            isPalindrome[left][right] = true;
            left--;
            right++;
        }
    }
}