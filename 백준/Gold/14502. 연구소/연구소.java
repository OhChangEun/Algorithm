import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, m;
    private static int[][] field;

    private static int[] dy = { -1, 1, 0, 0};
    private static int[] dx = { 0, 0, -1, 1};

    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);

        field = new int[n][m];
        for (int i = 0; i < n; i++) {
            parts = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                field[i][j] = Integer.parseInt(parts[j]);
            }
        }

        dfs(0);
        System.out.println(max);
    }

    private static void dfs(int depth) {
        if (depth == 3)  {
            int[][] spreaded = spreadVirus(field);
            countSafeArea(spreaded);
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (field[i][j] == 0) {
                    field[i][j] = 1;
                    dfs(depth + 1);
                    field[i][j] = 0;
                }
            }
        }
    }

    private static int[][] spreadVirus(int[][] field) {
        Queue<int[]> queue = new LinkedList<>();

        int[][] copyField = new int[n][m];
        for (int i = 0; i < n; i++) {
            copyField[i] = field[i].clone();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyField[i][j] == 2) {
                    queue.offer(new int[] {i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int ny = curr[0] + dy[dir];
                int nx = curr[1] + dx[dir];

                if (nx < 0 || ny < 0 || nx >= m ||  ny >= n) continue;
                if (copyField[ny][nx] == 0) {
                    copyField[ny][nx] = 2;
                    queue.offer(new int[] {ny, nx});
                }
            }
        }

        return copyField;
    }

    private static void countSafeArea(int[][] field) {
        int count = 0;
        for (int[] row: field) {
            for (int num: row) {
                if (num == 0) {
                    count++;
                }
            }
        }
        max = Math.max(max, count);
    }
}