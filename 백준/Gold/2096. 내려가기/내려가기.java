import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] maxDp = new int[3];
        int[] minDp = new int[3];

        for (int i=1; i<=N; i++) {
            String[] parts = br.readLine().split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            int c = Integer.parseInt(parts[2]);

            int max0 = maxDp[0], max1 = maxDp[1], max2 = maxDp[2];
            int min0 = minDp[0], min1 = minDp[1], min2 = minDp[2];

            maxDp[0] = a + Math.max(max0, max1);
            maxDp[1] = b + Math.max(max0, Math.max(max1, max2));
            maxDp[2] = c + Math.max(max1, max2);

            minDp[0] = a + Math.min(min0, min1);
            minDp[1] = b + Math.min(min0, Math.min(min1, min2));
            minDp[2] = c + Math.min(min1, min2);
        }

        Arrays.sort(maxDp);
        Arrays.sort(minDp);

        System.out.println(maxDp[2] + " " + minDp[0]);
    }
}
