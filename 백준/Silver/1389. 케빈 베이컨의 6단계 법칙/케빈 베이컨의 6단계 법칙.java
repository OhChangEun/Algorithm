import java.util.*;
import java.io.*;

public class Main {
    static int N, M;

    static int[][] graph;
    static boolean[] visited;

    public static int findMinIndex(int[] arr) {
        int min = 1;

        for (int i=2; i<arr.length; i++) {
            if (arr[i] < arr[min]) {
                min = i;
            }
        }
        return min;
    }

    public static int bfs(int start) {
        Arrays.fill(visited, false);
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        int sum = 0;
        int w = 1;
        while(!q.isEmpty()) {
            int size = q.size();

            for (int s=0; s<size; s++) {
                int curr = q.poll();

                for (int i=0; i<N+1; i++) {
                    if (graph[curr][i] == 1) {
                        if (!visited[i]) {
                            q.add(i);
                            visited[i] = true;
                            sum += w;
                            // System.out.println("i: " + i + " w: " + w);
                        }
                    }
                }
            }
            w++;
        }
        return sum;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 유저 수
        M = Integer.parseInt(st.nextToken()); // 친구 관계

        graph = new int[N+1][N+1];
        visited = new boolean[N+1];

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 시작점
            int v = Integer.parseInt(st.nextToken()); // 끝점

            graph[n][v] = 1;
            graph[v][n] = 1;
        }

        int[] counts = new int[N+1];

        for (int i=1; i<N+1; i++) {
            counts[i] = bfs(i);
            // System.out.println("i: " + counts[i]);
        }

        int res = findMinIndex(counts);
        System.out.println(res);
    }
}