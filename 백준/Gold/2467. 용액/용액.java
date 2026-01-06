import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        String[] parts = br.readLine().split(" ");

        int[] cups = new int[n];
        for (int i = 0; i < n; i++) {
            cups[i] = Integer.parseInt(parts[i]);
        }

        int left = 0;
        int right = n - 1;

        int minValue = Integer.MAX_VALUE;
        int minLeft = 0;
        int minRight = n - 1;
        while (left < right) {
            int sum = cups[left] + cups[right];
            if (Math.abs(sum) < Math.abs(minValue)) {
                minValue = sum;
                minLeft = left;
                minRight = right;
            }
            if (sum > 0) {
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                break;
            }
        }

        System.out.println(cups[minLeft] + " " + cups[minRight]);
    }
}