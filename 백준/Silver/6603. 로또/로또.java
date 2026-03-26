import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb;

    static int n;
    static int[] arr;
    static int[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = br.readLine();

            if (input.equals("0"))
                return;

            String[] parts = input.split(" ");

            n = Integer.parseInt(parts[0]);
            arr = new int[n];
            selected = new int[6];
            for (int i = 1; i <= n; i++) {
                arr[i - 1] = Integer.parseInt(parts[i]);
            }

            sb = new StringBuilder();
            dfs(0, 0);

            System.out.println(sb.toString());
        }
    }

    private static void dfs(int depth, int start) {
        if (depth == 6) {
            for (int num: selected) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < n; i++) {
            selected[depth] = arr[i];
            dfs(depth + 1, i + 1);
        }
    }
}