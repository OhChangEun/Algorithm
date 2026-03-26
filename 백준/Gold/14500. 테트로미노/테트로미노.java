import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] map;

    static boolean[][] visited;

    static int maxValue = 0;
    static int[] dy = { -1 , 1, 0 , 0 };
    static int[] dx = { 0 , 0, -1 , 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");

        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);

        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            parts = br.readLine().split(" ");

            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(parts[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, 0, map[i][j]);
                visited[i][j] = false;

                checkT(i, j);
            }
        }

        System.out.println(maxValue);
    }

    static void checkT(int y, int x) {
        int count = 0;
        int sum = map[y][x];
        int minValue = Integer.MAX_VALUE;

        for (int dir = 0; dir < 4; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;

            sum += map[ny][nx];
            minValue = Math.min(minValue, map[ny][nx]);
            count++;
        }

        if (count == 4) {
            sum -= minValue;
        }
        
        if (count >= 3) {
            maxValue = Math.max(maxValue, sum);
        }
    }

    static void dfs(int i, int j, int depth, int value) {
        if (depth == 3) {
            maxValue = Math.max(maxValue, value);
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int ny = i + dy[dir];
            int nx = j + dx[dir];

            if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;

            if (!visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(ny, nx, depth + 1, value + map[ny][nx]);
                visited[ny][nx] = false;
            }
        }
    }
}