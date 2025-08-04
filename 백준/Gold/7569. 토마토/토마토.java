import java.util.*;
import java.io.*;

public class Main {
    static int M;
    static int N;
    static int H;

    static int box[][][];

    static int[] dx = { 0, 0, 0, 0, -1 , 1};
    static int[] dy = { 0, 0, -1, 1, 0 , 0};
    static int[] dz = { -1, 1, 0, 0, 0 , 0};

    static class Point {
        int x, y, z;
        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        for (int i=0; i<H; i++) {
            for (int j=0; j<N; j++) {
                for (int k=0; k<M; k++) {
                    // 모든 익은 과일들 queue에 넣기
                    if (box[i][j][k] == 1)
                        queue.add(new Point(i, j, k));
                }
            }
        }

        while (!queue.isEmpty()) {
            Point curr = queue.poll();

            for (int i=0; i<6; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                int nz = curr.z + dz[i];

                if (nx >= 0 && nx < H && ny >= 0 && ny < N && nz >= 0 && nz < M) {
                    if (box[nx][ny][nz] == 0) {
                        box[nx][ny][nz] = box[curr.x][curr.y][curr.z] + 1;
                        queue.add(new Point(nx, ny, nz));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        M = Integer.parseInt(parts[0]);
        N = Integer.parseInt(parts[1]);
        H = Integer.parseInt(parts[2]);

        box = new int[H][N][M];

        for (int i=0; i<H; i++) {
            for (int j=0; j<N; j++) {
                String[] nums = br.readLine().split(" ");
                for (int k=0; k<M; k++) {
                    box[i][j][k] = Integer.parseInt(nums[k]);
                }
            }
        }

        boolean isAllRipened = true;
        for (int i=0; i<H; i++) {
            for (int j=0; j<N; j++) {
                for (int k=0; k<M; k++) {
                    if (box[i][j][k] == 0)
                        isAllRipened = false;
                }
            }
        }

        if (isAllRipened) {
            System.out.println(0);
            return;
        }

        bfs(); // 모든 토마토에 대해 순회

        int max = 0;
        for (int i=0; i<H; i++) {
            for (int j=0; j<N; j++) {
                for (int k=0; k<M; k++) {
                    if (box[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    max = Math.max(max, box[i][j][k]);
                }
            }
        }

        System.out.println(max - 1);
    }
}
