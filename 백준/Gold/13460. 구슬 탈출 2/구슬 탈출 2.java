import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] map;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class State {
        int ry, rx;
        int by, bx;
        int count;

        public State(int ry, int rx, int by, int bx, int count) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);

        map = new char[n][m];

        int ry = 0, rx = 0;
        int by = 0, bx = 0;
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                char ch = input[j];
                if (ch == 'R') {
                    ry = i;
                    rx = j;
                    map[i][j] = '.';
                } else if (ch == 'B') {
                    by = i;
                    bx = j;
                    map[i][j] = '.';
                } else {
                    map[i][j] = ch;
                }
            }
        }

        int minCount = bfs(ry, rx, by, bx);
        System.out.println(minCount);

    }

    private static int bfs(int ry, int rx, int by, int bx) {
        String key = getKey(ry, rx, by, bx);

        Set<String> visited = new HashSet<>();
        visited.add(key);

        ArrayDeque<State> queue = new ArrayDeque<>();
        queue.offer(new State(ry, rx, by, bx, 0));

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            if (curr.count >= 10) continue;

            for (int dir = 0; dir < 4; dir++) {
                int nry = curr.ry;
                int nrx = curr.rx;
                boolean isRedIn = false;

                while (true) {
                    int ty = nry + dy[dir];
                    int tx = nrx + dx[dir];

                    if (map[ty][tx] == '#') break;

                    nry = ty;
                    nrx = tx;
                    if (map[nry][nrx] == 'O') {
                        isRedIn = true;
                        break;
                    }
                }

                int nby = curr.by;
                int nbx = curr.bx;
                boolean isBlueIn = false;

                while (true) {
                    int ty = nby + dy[dir];
                    int tx = nbx + dx[dir];

                    if (map[ty][tx] == '#') break;

                    nby = ty;
                    nbx = tx;
                    if (map[nby][nbx] == 'O') {
                        isBlueIn = true;
                        break;
                    }
                }

                if (isBlueIn) continue;

                if (isRedIn)
                    return curr.count + 1;

                // 두 구슬이 좌표가 같다면 먼 거리의 구슬을 한칸 멀리 배치
                if (nry == nby && nrx == nbx) {
                    int redDist = Math.abs(nry - curr.ry) + Math.abs(nrx - curr.rx);
                    int blueDist = Math.abs(nby - curr.by) + Math.abs(nbx - curr.bx);

                    if (redDist > blueDist) {
                        nry -= dy[dir];
                        nrx -= dx[dir];
                    } else {
                        nby -= dy[dir];
                        nbx -= dx[dir];
                    }
                }

                String newKey = getKey(nry, nrx, nby, nbx);
                if (!visited.contains(newKey)) {
                    visited.add(newKey);
                    queue.offer(new State(nry, nrx, nby, nbx, curr.count + 1));
                }
            }
        }

        return -1;
    }

    private static String getKey(int ry, int rx, int by, int bx) {
        return ry + ", " + rx + ", " + by + ", " + bx;
    }
}