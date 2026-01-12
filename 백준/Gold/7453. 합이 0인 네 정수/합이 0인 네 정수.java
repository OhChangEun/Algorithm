import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] a, b, c, d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        a = new int[n];
        b = new int[n];
        c = new int[n];
        d = new int[n];
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            a[i] = Integer.parseInt(parts[0]);
            b[i] = Integer.parseInt(parts[1]);
            c[i] = Integer.parseInt(parts[2]);
            d[i] = Integer.parseInt(parts[3]);
        }

        int idx = 0;
        int[] sumAB = new int[n * n];
        int[] sumCD = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumAB[idx] = a[i] + b[j];
                sumCD[idx] = c[i] + d[j];
                idx++;
            }
        }

        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        int left = 0;
        int right = n * n - 1;
        long total = 0;
        while (left < n * n && right >= 0) {
            int sum = sumAB[left] + sumCD[right];

            long countAB = 1;
            long countCD = 1;
            if (sum == 0) {
                while (left + 1 < n * n && sumAB[left] == sumAB[left + 1]) {
                    left++;
                    countAB++;
                }

                while (right - 1 >= 0 && sumCD[right] == sumCD[right - 1]) {
                    right--;
                    countCD++;
                }
                
                total += countAB * countCD;
                left++;
                right--;
            } else if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(total);

    }
}