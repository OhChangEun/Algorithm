import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            parts = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(parts[j]);
            }
        }

        int minTime = 0;
        while (!allRemove()) {
            bfs();
            update();
            //printVisited();
            //print();
            minTime++;
        }
        System.out.println(minTime);
    }

    private static boolean[][] visited;
    private static int[] dx = { 0, 0, -1, 1 };
    private static int[] dy = { -1, 1, 0, 0 };
    private static void bfs() {
        visited = new boolean[n][m];
        visited[0][0] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cy = curr[0];
            int cx = curr[1];

            for (int d = 0; d < 4; d++) {
                int ny = cy + dy[d];
                int nx = cx + dx[d];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (!visited[ny][nx] && map[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    queue.offer(new int[] {ny, nx});
                }
            }
        }
    }

    private static void update() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // map에서 치즈 중에서
                if (map[i][j] == 1) {
                    List<int[]> airList = new ArrayList<>();

                    // 상하좌우를 검사해서
                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];

                        if (visited[ny][nx]) {
                            airList.add(new int[] {i, j});
                            //System.out.println(ny + ", " + nx);
                        }
                    }
                    //System.out.println();

                    // 외부 공기와 닿는 면적이 2개 이상이면 1로 변환
                    if (airList.size() >= 2) {
                        for (int[] air: airList) {
                            map[air[0]][air[1]] = 0;
                        }
                    }
                }
            }
        }
    }

    private static boolean allRemove() {
        for (int[] row: map) {
            for (int num: row) {
                if (num == 1)
                    return false;
            }
        }
        return true;
    }
    private static void printVisited() {
        for (boolean[] row: visited) {
            for (boolean num: row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void print() {
        for (int[] row: map) {
            for (int num: row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}