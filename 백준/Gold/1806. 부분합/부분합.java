import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n, s;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        n = Integer.parseInt(parts[0]);
        s = Integer.parseInt(parts[1]);

        arr = new int[n];
        parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }

        int left = 0;
        long currSum = 0;
        int minLength = Integer.MAX_VALUE;
        for (int right = 0; right < n; right++) {
            currSum += (long)arr[right];

            while (currSum >= s) {
                minLength = Math.min(minLength, right - left + 1);
                currSum -= (long)arr[left];
                left++;
            }
        }

        int result = 0;
        if (minLength == Integer.MAX_VALUE) {
            result = 0;
        } else {
            result = minLength;
        }

        System.out.println(result);

    }
}