import java.io.*;
import java.util.*;

public class Main {
    static int M;
    static int N;
    static int H;

    static int box[][][];
    static boolean isVisited[][][];

    // 위, 아레, 상, 하, 좌, 우
    static int[] dx = { 0, 0, 0, 0, -1, 1};
    static int[] dy = { 0, 0, -1, 1, 0, 0};
    static int[] dz = { -1, 1, 0, 0, 0, 0};

    static class Box {
        int x, y, z;
        public Box(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void bfs() {
        // 큐에 방문하지 않은 점 집어 넣고,
        Queue<Box> queue = new LinkedList<>();

        // 상자에 있는 모든 익은 토마토 큐에 삽입
        for (int i=0; i<H; i++) {
            for (int j=0; j<N; j++) {
                for (int k=0; k<M; k++) {
                    if (box[i][j][k] == 1) {
                        queue.add(new Box(i, j, k));
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            Box curr = queue.poll();

            for (int n=0; n<6; n++) {
                int nx = curr.x + dx[n];
                int ny = curr.y + dy[n];
                int nz = curr.z + dz[n];

                if (nx >= 0 && nx < H && ny >= 0 && ny < N && nz >= 0 && nz < M) {
                    if (box[nx][ny][nz] == 0) {
                        box[nx][ny][nz] = box[curr.x][curr.y][curr.z] + 1;
                        queue.add(new Box(nx, ny, nz));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int [H][N][M];
        isVisited = new boolean [H][N][M];

        for (int i=0; i<H; i++) {
            for (int j=0; j<N; j++) {
                String[] nums = br.readLine().split(" ");
                for (int k=0; k<M; k++) {
                    box[i][j][k] = Integer.parseInt(nums[k]);
                }
            }
        }

        // 박스에 값이 0이 없다면 0 출력
        boolean hasUnripe = false;
        for (int i=0; i<H; i++) {
            for (int j=0; j<N; j++) {
                for (int k=0; k<M; k++) {
                    if (box[i][j][k] == 0) {
                        hasUnripe = true;
                    }
                }
            }
        }

        if (!hasUnripe) {
            System.out.println(0);
            return;
        }

        bfs();

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
        System.out.println(max-1);
    }
}
