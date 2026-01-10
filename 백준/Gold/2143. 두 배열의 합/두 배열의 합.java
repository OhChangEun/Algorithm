import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long t = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        String[] parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(parts[i]);
        }

        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];
        parts = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(parts[i]);
        }

        // map<합계, 개수>
        Map<Long, Integer> sumMapA = new HashMap<>();
        Map<Long, Integer> sumMapB = new HashMap<>();

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += a[j];
                sumMapA.put(sum, sumMapA.getOrDefault(sum, 0) + 1);
            }
        }

        for (int i = 0; i < m; i++) {
            long sum = 0;
            for (int j = i; j < m; j++) {
                sum += b[j];
                sumMapB.put(sum, sumMapB.getOrDefault(sum, 0) + 1);
            }
        }

        long count = 0;
        for (long keyA: sumMapA.keySet()) {
            long diff = t - keyA;
            if (sumMapB.containsKey(diff)) {
               count += (long)sumMapA.get(keyA) * sumMapB.get(diff);
            }
        }

        System.out.println(count);
    }
}