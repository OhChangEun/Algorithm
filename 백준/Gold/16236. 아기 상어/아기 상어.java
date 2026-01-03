import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] map;

    public static int[] dx = { 0, -1, 0, 1 };
    public static int[] dy = { -1, 0, 1, 0 };

    static int sharkSize = 2;

    static class Fish {
        int y, x;
        int dist;
        public Fish(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        int startY = 0, startX = 0;
        n = Integer.parseInt(parts[0]);
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            parts = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(parts[j]);
                map[i][j] = num;

                if (num == 9) {
                    startY = i;
                    startX = j;
                    map[i][j] = 0;
                }
            }
        }

        int eatCount = 0;
        int minTime = 0;
        while (true) {
            Fish target = bfs(startY, startX);
            if (target == null)
                break;

            startY = target.y;
            startX = target.x;
            minTime += target.dist;
            eatCount++;
            map[startY][startX] = 0;

            if (eatCount >= sharkSize) {
                sharkSize++;
                eatCount = 0;
            }
        }

        System.out.println(minTime);
    }
    private static void print() {
        for (int[] row: map) {
            for (int num: row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
    private static Fish bfs(int y, int x) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }
        dist[y][x] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {y, x});

        List<Fish> fishList = new ArrayList<>();

        int minDist = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cy = curr[0];
            int cx = curr[1];

            if (minDist < dist[cy][cx]) continue;

            // 방문할 수 있는 모든 물고기를
            if (map[cy][cx] != 0 && map[cy][cx] < sharkSize) {
                minDist = dist[cy][cx];
                fishList.add(new Fish(cy, cx, dist[cy][cx]));
                continue;
                // System.out.println(cy + ", " + cx);
            }

            for (int d = 0; d < 4; d++) {
                int ny = cy + dy[d];
                int nx = cx + dx[d];

                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                // 방문한 적이 없거나, 물고기가 나보다 작거나 같아야 함
                if (dist[ny][nx] == -1 && map[ny][nx] <= sharkSize) {
                    dist[ny][nx] = dist[cy][cx] + 1;
                    queue.offer(new int[] {ny, nx});
                }
            }
        }

        if (fishList.isEmpty()) return null;

        fishList.sort((a, b) -> {
            if (a.dist != b.dist) return a.dist - b.dist;
            if (a.y != b.y) return a.y - b.y;
            return a.x - b.x;
        });

        return fishList.get(0);
    }
}