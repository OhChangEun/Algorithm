import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] cups = new long[n];

        String[] parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            cups[i] = Long.parseLong(parts[i]);
        }

        Arrays.sort(cups);

        long minSum = Long.MAX_VALUE;
        long resA = 0, resB = 0, resC = 0;
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                long sum = cups[i] + cups[left] + cups[right];
                if (Math.abs(sum) < Math.abs(minSum)) {
                    minSum = sum;
                    resA = cups[i];
                    resB = cups[left];
                    resC = cups[right];
                }

                if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        System.out.println(resA + " " + resB + " " + resC);
    }
}