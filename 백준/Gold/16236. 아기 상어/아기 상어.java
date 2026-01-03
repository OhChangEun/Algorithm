import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] map;
    static int fishSize = 2;
    static int minTime = 0;
    static int eatCount = 0;
    static class Node implements Comparable<Node> {
        int x, y;
        int dist;
        public Node(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node other) {
            if (this.dist != other.dist) return this.dist - other.dist;
            if (this.y != other.y) return this.y - other.y;
            return this.x - other.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        int startY = 0, startX = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(parts[j]);

                map[i][j] = num;
                if (num == 9) {
                    map[i][j] = 0;
                    startY = i;
                    startX = j;
                }
            }
        }

        while (true) {
            Node fish = bfs(startY, startX);
            if (fish == null) break;

            startY = fish.y;
            startX = fish.x;
            minTime += fish.dist;
            eatCount++;
            map[startY][startX] = 0;

            if (eatCount >= fishSize) {
                fishSize++;
                eatCount = 0;
            }
        }

        System.out.println(minTime);
    }

    static int[] dy = { -1, 0, 1, 0 };
    static int[] dx = { 0, -1, 0, 1 };

    private static Node bfs(int y, int x) {
        boolean[][] visited = new boolean[n][n];
        visited[y][x] = true;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(y, x, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int cy = curr.y;
            int cx = curr.x;
            if (map[cy][cx] != 0 && map[cy][cx] < fishSize) {
                return curr;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cy + dy[d];
                int nx = cx + dx[d];

                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                if (visited[ny][nx]) continue;

                if (map[ny][nx] == 0 || map[ny][nx] <= fishSize) {
                    visited[ny][nx] = true;
                    pq.offer(new Node(ny, nx, curr.dist + 1));
                }
            }
        }

        return null;
    }
}