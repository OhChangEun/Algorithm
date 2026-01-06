import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n + 1][2];
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);

            arr[i][0] = x;
            arr[i][1] = y;
        }
        arr[n][0] = arr[0][0];
        arr[n][1] = arr[0][1];

        /*
        for (int[] row: arr) {
            for (int num: row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        */

        long sum1 = 0;
        for (int i = 0; i < n ; i++) {
            sum1 += (long)arr[i][0] * arr[i + 1][1];
        }
        long sum2 = 0;
        for (int i = 0; i < n ; i++) {
            sum2 += (long)arr[i][1] * arr[i + 1][0];
        }

        double result = Math.abs(sum1 - sum2) / 2.0;
        System.out.printf("%.1f", result);
    }
}