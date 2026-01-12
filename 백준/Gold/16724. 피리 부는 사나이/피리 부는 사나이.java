import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");
        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        parent = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            parent[i] = i;
        }

        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                }
            }
        }

        Set<Integer> cycles = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cycles.add(find(i * m + j));
            }
        }

        System.out.println(cycles.size());
    }

    private static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
    private static void bfs(int y, int x) {
        visited[y][x] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {y, x});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cy = curr[0];
            int cx = curr[1];

            int dir = getDir(map[cy][cx]);
            int ny = cy + dy[dir];
            int nx = cx + dx[dir];

            int currNode = cy * m + cx;
            int nextNode = ny * m + nx;
            union(currNode, nextNode);

            if (!visited[ny][nx]) {
                visited[ny][nx] = true;
                queue.offer(new int[] {ny, nx});
            }
        }
    }

    static void print() {
        for (char[] row: map) {
            for (char num: row) {
                System.out.print(num);
            }
            System.out.println();
        }
    }

    private static int getDir(char op) {
        switch (op) {
            case 'U': return 0;
            case 'D': return 1;
            case 'L': return 2;
            case 'R': return 3;
            default : return -1;
        }
    }
}