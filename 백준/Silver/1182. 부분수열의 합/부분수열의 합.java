import java.io.*;
import java.util.*;

public class Main {
    static int n, s;
    static int[] arr;
    static int[] selected;

    static int cnt = 0;

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

        for (int i = 1; i <= n; i++) {
            selected = new int[n];
            dfs(i, 0, 0);
        }

        System.out.println(cnt);
    }

    static void dfs(int target, int depth, int start) {
        if (depth == target) {
            int sum = getSum(selected);
            if (sum == s)
                cnt++;
            return;
        }

        for (int i = start; i < n; i++) {
            selected[depth] = arr[i];
            dfs(target, depth + 1, i + 1);
        }
    }

    static int getSum(int[] arr) {
        int sum = 0;
        for (int num: arr) {
            sum += num;
        }
        return sum;
    }

}