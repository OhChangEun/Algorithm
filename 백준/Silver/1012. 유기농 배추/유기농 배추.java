import java.io.*;
import java.util.*;

public class Main {
    static int[][] field;
    static boolean[][] visited;

    static int M;
    static int N;
    static int K;

    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void dfs(int x, int y) {
        visited[y][x] = true;

        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < M && ny >= 0 && ny < N) { // 범위 설정
                if (field[ny][nx] == 1 && !visited[ny][nx]) { // 방문하지 않은 배추
                    dfs(nx, ny);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 배추밭 가로길이
            N = Integer.parseInt(st.nextToken()); // 배추밭 세로길이
            K = Integer.parseInt(st.nextToken()); // 배추 개수

            field = new int[N][M];
            visited = new boolean[N][M];

            // 배추밭 초기화
            for (int j=0; j<K; j++) {
                String[] input = br.readLine().split(" ");
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);

                field[y][x] = 1;
            }

            int wormCount = 0; // 배추 흰지렁이 개수
            for (int y=0; y<N; y++) {
                for (int x=0; x<M; x++) {
                    if (field[y][x] == 1 && !visited[y][x]) {
                        dfs(x, y);
                        wormCount++;
                    }
                }
            }
            System.out.println(wormCount); // 배추 흰지렁이 개수 출력
        }
    }
}
