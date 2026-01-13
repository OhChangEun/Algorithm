import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MOD = 10;
    static int n, m;
    static int[][] map;
    static int[][] areaId;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Point {
        int y, x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");

        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);
        map = new int[n][m];
        areaId = new int[n][m];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                int num = input.charAt(j) - '0';
                map[i][j] = num;
            }
        }


        int id = 1;
        Map<Integer, Integer> areaSize = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && areaId[i][j] == 0) {
                    int dist = bfs(i, j, id);
                    areaSize.put(id, dist);
                    id++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    int sum = 1;
                    Set<Integer> closeItems = new HashSet<>();
                    for (int dir = 0; dir < 4; dir++) {
                        int ny = i + dy[dir];
                        int nx = j + dx[dir];

                        if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;

                        int closeItem = areaId[ny][nx];
                        if (closeItem != 0) {
                            closeItems.add(closeItem);
                        }
                    }

                    for (int closeItem: closeItems) {
                        sum += areaSize.get(closeItem);
                    }
                    sb.append(sum % MOD);
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int bfs(int y, int x, int id) {
        int dist = 0;

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(y, x));
        areaId[y][x] = id;

        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            int cy = curr.y;
            int cx = curr.x;

            dist++;

            for (int d = 0; d < 4; d++) {
                int ny = cy + dy[d];
                int nx = cx + dx[d];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (map[ny][nx] == 0 && areaId[ny][nx] == 0) {
                    areaId[ny][nx] = id;
                    queue.offer(new Point(ny, nx));
                }
            }
        }

        return dist % MOD;
    }
}