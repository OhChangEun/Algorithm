import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static boolean[][] visited;
    static boolean[][] light;
    static List<int[]>[][] switchList;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");
        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);


        switchList = new ArrayList[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                switchList[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            parts = br.readLine().split(" ");
            int cy = Integer.parseInt(parts[0]);
            int cx = Integer.parseInt(parts[1]);
            int ny = Integer.parseInt(parts[2]);
            int nx = Integer.parseInt(parts[3]);

            switchList[cy][cx].add(new int[] {ny, nx});
        }

        visited = new boolean[n + 1][n + 1];
        light = new boolean[n + 1][n + 1];

        bfs();

        int cnt = 0;
        for (int i = 1 ; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (light[i][j]) cnt++;
            }
        }

        System.out.println(cnt);
    }


    private static void bfs() {
        visited[1][1] = true;
        light[1][1] = true;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {1, 1});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cy = curr[0];
            int cx = curr[1];

            for (int[] next: switchList[cy][cx]) {
                int ly = next[0];
                int lx = next[1];

                if (light[ly][lx]) continue;
                light[ly][lx] = true;

                for (int dir = 0; dir < 4; dir++) {
                    int ny = ly + dy[dir];
                    int nx = lx + dx[dir];

                    if (ny < 1 || ny > n || nx < 1 || nx > n) continue;
                    if (visited[ny][nx]) {
                        visited[ly][lx] = true;
                        queue.offer(new int[] {ly, lx});
                        break;
                    }
                }
            }

            for (int dir = 0; dir < 4; dir++) {
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];

                if (ny < 1 || ny > n || nx < 1 || nx > n) continue;
                if (!light[ny][nx]) continue;

                if (!visited[ny][nx]) {
                    visited[ny][nx] = true;
                    queue.offer(new int[] {ny, nx});
                }
            }
        }
    }
}