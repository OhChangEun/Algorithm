import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static char[][] graph;
    static char[][] graph2;
    static boolean[][] visited;

    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };

    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(char[][] graph, int x, int y) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();

            for (int i=0; i<4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx >= 0 && nx < N && ny >=0 && ny < N) {
                    if (graph[nx][ny] == graph[curr.x][curr.y] && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new Pair(nx, ny));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new char[N][N];
        graph2 = new char[N][N];
        visited = new boolean[N][N];

        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<N; j++) {
                graph[i][j] = line.charAt(j);
                if (line.charAt(j) == 'R') {
                    graph2[i][j] = 'G';
                } else {
                    graph2[i][j] = line.charAt(j);
                }
            }
        }

        int count = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (!visited[i][j]) {
                    bfs(graph, i, j);
                    count++;
                }
            }
        }

        int count2 = 0;
        for (int i=0; i<N; i++) {
            Arrays.fill(visited[i], false);
        }
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (!visited[i][j]) {
                    bfs(graph2, i, j);
                    count2++;
                }
            }
        }
        System.out.println(count + " " + count2);

//        for (int i=0; i<N; i++) {
//            for (int j=0; j<N; j++) {
//                System.out.print(graph[i][j]);
//            }
//            System.out.println();
//        }
    }
}
