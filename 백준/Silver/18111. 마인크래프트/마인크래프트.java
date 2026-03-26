import java.io.*;
import java.util.*;

public class Main {

    static int n, m, b;
    static int[][] map;

    static int minValue = Integer.MAX_VALUE;
    static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");
        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);
        b = Integer.parseInt(parts[2]);

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            parts = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(parts[j]);
            }
        }

        for (int[] row: map) {
            for (int num : row) {
                minValue = Math.min(minValue, num);
                maxValue = Math.max(maxValue, num);
            }
        }

        int minTime = Integer.MAX_VALUE;
        int height = 0;
        for (int target = maxValue; target >= minValue; target--) {
            int need = 0;
            int left = 0;
            int time = 0;
            for (int[] row: map) {
                for (int num : row) {
                    if (num < target) {
                        need += target - num;
                    } else if (num > target) {
                        left += num - target;
                    }
                }
            }

            if (need > b + left) continue; // 다 쌓을 수 없을 때

            time = left * 2 + need;
            if (minTime > time) {
                minTime = time;
                height = target;
            }
        }

        System.out.println(minTime + " " + height);
    }
}
