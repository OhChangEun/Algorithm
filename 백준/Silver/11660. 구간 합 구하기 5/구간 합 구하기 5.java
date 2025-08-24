import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        int N = Integer.parseInt(parts[0]);
        int M = Integer.parseInt(parts[1]);

        int[][] arr = new int[N+1][N+1]; // 원본 배열 (1-based)
        int[][] psum = new int[N+1][N+1]; // 누적합 배열 (1-based)

        for (int i=1; i<=N; i++) {
            parts = br.readLine().split(" ");
            for (int j=1; j<=N; j++) {
                arr[i][j] = Integer.parseInt(parts[j-1]);
            }
        }

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                psum[i][j] = psum[i-1][j] + psum[i][j-1] - psum[i-1][j-1] + arr[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<M; i++) {
            parts = br.readLine().split(" ");

            int x1 = Integer.parseInt(parts[0]);
            int y1 = Integer.parseInt(parts[1]);
            int x2 = Integer.parseInt(parts[2]);
            int y2 = Integer.parseInt(parts[3]);

            int result = psum[x2][y2] - psum[x2][y1-1] - psum[x1-1][y2] + psum[x1-1][y1-1];
            sb.append(result).append("\n");
        }
        System.out.print(sb.toString());

        /*
        for (int i=0; i<=N; i++) {
            for (int j=0; j<=N; j++) {
                System.out.print(psum[i][j] + " ");
            }
            System.out.println();
        }
        */

    }
}