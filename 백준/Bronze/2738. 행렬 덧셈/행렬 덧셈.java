import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        int[][] arr2 = new int[N][M];
        int[][] res = new int[N][M];
        for (int i=0; i<N; i++) {
            String[] parts = br.readLine().split(" ");
            for (int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(parts[j]);
            }
        }
        for (int i=0; i<N; i++) {
            String[] parts = br.readLine().split(" ");
            for (int j=0; j<M; j++) {
                arr2[i][j] = Integer.parseInt(parts[j]);
            }
        }
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                res[i][j] = arr[i][j] + arr2[i][j];
            }
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}