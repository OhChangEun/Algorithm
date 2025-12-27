import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int n, m;
    private static int[][] field;
    private static int[] dx = { -1, 1, 0, 0 };
    private static int[] dy = { 0, 0, -1, 1 };

    static class Node {
        int x, y;
        int dist;
        int broke;

        public Node(int x, int y, int dist, int broke) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.broke = broke;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);

        field = new int[n][m];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                field[i][j] = input.charAt(j) - '0';
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    private static int bfs() {
        boolean[][][] visited = new boolean[n][m][2];
        visited[0][0][0] = true;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 1, 0));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.x == n - 1 && curr.y == m - 1) {
                return curr.dist;
            }

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (field[nx][ny] == 0 && !visited[nx][ny][curr.broke]) {
                    visited[nx][ny][curr.broke] = true;
                    queue.offer(new Node(nx, ny, curr.dist + 1, curr.broke));
                }

                if (field[nx][ny] == 1 && curr.broke == 0 && !visited[nx][ny][1]) {
                    visited[nx][ny][1] = true;
                    queue.offer(new Node(nx, ny, curr.dist + 1, 1));
                }
            }
        }

        return -1;
    }
}