import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] graph = new int[n][n];
        for (int i=0; i<n; i++) {
            String[] parts = br.readLine().split(" ");
            for (int j=0; j<=i; j++) {
                graph[i][j] = Integer.parseInt(parts[j]);
            }
        }

        for (int i=1; i<n; i++) {
            graph[i][0] += graph[i-1][0];
            for (int j=1; j<i; j++) {
                graph[i][j] += Math.max(graph[i-1][j-1], graph[i-1][j]); // 이전 노드 후보
            }
            graph[i][i] += graph[i-1][i-1];
        }

        Arrays.sort(graph[n-1]); // 내림차순 정렬
        System.out.print(graph[n-1][n-1]); // 결과의 최대값
    }
}