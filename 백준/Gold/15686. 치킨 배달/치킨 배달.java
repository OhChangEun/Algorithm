import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;

    static List<int[]> chickens;
    static List<int[]> houses;

    static int[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);

        map = new int[n][n];
        chickens = new ArrayList<>();
        houses = new ArrayList<>();
        selected = new int[m];

        for (int i = 0; i < n; i++) {
            parts = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(parts[j]);
                map[i][j] = num;

                if (num == 2) {
                    chickens.add(new int[] {i, j});
                } else if (num == 1) {
                    houses.add(new int[] {i, j});
                }
            }
        }

        dfs(0, 0);
        System.out.println(result);
    }

    private static int result = Integer.MAX_VALUE;
    private static void dfs(int depth, int curr) {
        if (depth == m) {
            result = Math.min(result, getDist());
            /*
            for (int num: selected) {
                System.out.print(num + " ");
            }
            System.out.println();
             */
            return;
        }

        for (int i = curr; i < chickens.size(); i++) {
            selected[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }

    private static int getDist() {
        int dist = 0;

        for (int[] house: houses) {
            int minDist = Integer.MAX_VALUE;
            for (int idx: selected) {
                int y = chickens.get(idx)[0];
                int x = chickens.get(idx)[1];

                minDist = Math.min(minDist, Math.abs(house[0] - y) + Math.abs(house[1] - x));
            }
            dist += minDist;
        }

        return dist;
    }
}