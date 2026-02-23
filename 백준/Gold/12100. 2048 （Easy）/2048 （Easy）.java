import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int maxValue = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(parts[j]);
            }
        }

        dfs(0, map);

        System.out.println(maxValue);
    }

    private static void moveUp(int[][] map) {
        for (int j = 0; j < n; j++) {
            boolean[] merged = new boolean[n];
            for (int i = 1; i < n; i++) {
                int ny = i;

                int value = map[i][j];
                if (value == 0) continue;
                while (true) {
                    int ty = ny - 1;

                    if (ty < 0) break;
                    if (map[ty][j] != 0) break;

                    ny = ty;
                    map[i][j] = 0;
                }

                map[ny][j] = value;
                if (ny - 1 >= 0 && !merged[ny - 1] && map[ny - 1][j] == value) {
                    merged[ny - 1] = true;
                    map[ny - 1][j] = value * 2;
                    map[ny][j] = 0;
                }
            }
        }
    }

    private static void moveDown(int[][] map) {
        for (int j = 0; j < n; j++) {
            boolean[] merged = new boolean[n];
            for (int i = n - 2; i >= 0; i--) {
                int ny = i;

                int value = map[i][j];
                if (value == 0) continue;
                while (true) {
                    int ty = ny + 1;

                    if (ty > n - 1) break;
                    if (map[ty][j] != 0) break;

                    ny = ty;
                    map[i][j] = 0;
                }

                map[ny][j] = value;
                if (ny + 1 < n && !merged[ny + 1] && map[ny + 1][j] == value) {
                    merged[ny + 1] = true;
                    map[ny + 1][j] = value * 2;
                    map[ny][j] = 0;
                }
            }
        }
    }

    private static void moveLeft(int[][] map) {
        for (int i = 0; i < n; i++) {
            boolean[] merged = new boolean[n];
            for (int j = 1; j < n; j++) {
                int nx = j;

                int value = map[i][j];
                if (value == 0) continue;
                while (true) {
                    int tx = nx - 1;

                    if (tx < 0) break;
                    if (map[i][tx] != 0) break;

                    nx = tx;
                    map[i][j] = 0;
                }

                map[i][nx] = value;
                if (nx - 1 >= 0 && !merged[nx - 1] && map[i][nx - 1] == value) {
                    merged[nx - 1] = true;
                    map[i][nx - 1] = value * 2;
                    map[i][nx] = 0;
                }
            }
        }
    }

    private static void moveRight(int[][] map) {
        for (int i = 0; i < n; i++) {
            boolean[] merged = new boolean[n];
            for (int j = n - 2; j >= 0; j--) {
                int nx = j;

                int value = map[i][j];
                if (value == 0) continue;
                while (true) {
                    int tx = nx + 1;

                    if (tx > n - 1) break;
                    if (map[i][tx] != 0) break;

                    nx = tx;
                    map[i][j] = 0;
                }

                map[i][nx] = value;
                if (nx + 1 < n && !merged[nx + 1] && map[i][nx + 1] == value) {
                    merged[nx + 1] = true;
                    map[i][nx + 1] = value * 2;
                    map[i][nx] = 0;
                }
            }
        }
    }

    private static void move(int[][] map, int op) {
        if (op == 0) {
            moveUp(map);
        } else if (op == 1) {
            moveDown(map);
        } else if (op == 2) {
            moveLeft(map);
        } else if (op == 3) {
            moveRight(map);
        }
    }

    private static void dfs(int depth, int[][] map) {
        if (depth == 5) {
            getMax(map);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][] copy = copyMap(map);
            move(copy, i);
            dfs(depth + 1, copy);
        }
    }

    private static int[][] copyMap(int[][] map) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            copy[i] = map[i].clone();
        }

        return copy;
    }

    private static void getMax(int[][] map) {
        for (int[] row: map) {
            for (int num: row) {
                maxValue = Math.max(maxValue, num);
            }
        }
    }
}