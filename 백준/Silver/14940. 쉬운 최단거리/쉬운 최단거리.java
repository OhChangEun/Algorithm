import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] field;
    static boolean[][] visited;
    static int[][] distance;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void bfs(int x, int y) {
        Queue<int []> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        distance[x][y] = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cx = curr[0];
            int cy = curr[1];

            for (int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < N  && ny >= 0 && ny < M ) {
                    if (field[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        distance[nx][ny] = distance[cx][cy] + 1;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new int[N][M];
        visited = new boolean[N][M];
        distance = new int[N][M];

        int sPoint = 0;
        int ePoint = 0;
        for (int i=0; i<N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j=0; j<M; j++) {
                field[i][j] = Integer.parseInt(line[j]);
                if (field[i][j] == 2) {
                    sPoint = i;
                    ePoint = j;
                }
            }
        }

        bfs(sPoint, ePoint);
        //System.out.println(res);
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (field[i][j] == 1 && distance[i][j] == 0) {
                    System.out.print(-1 + " ");
                } else {
                    System.out.print(distance[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
