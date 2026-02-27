import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static int[] lis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        String[] parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }

        int len = 0;
        lis = new int[n];

        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = len - 1;
            int target = len;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (arr[lis[mid]] >= arr[i]) {
                    target = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            lis[target] = i;
            if (target == len) {
                len++;
            }
        }

        System.out.println(len);
    }
}