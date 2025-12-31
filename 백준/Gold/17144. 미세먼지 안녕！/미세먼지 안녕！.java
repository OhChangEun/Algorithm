import java.util.*;
import java.io.*;

public class Main {
    static int row;
    static int col;

    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        row = Integer.parseInt(parts[0]);
        col = Integer.parseInt(parts[1]);
        int t = Integer.parseInt(parts[2]);

        map = new int[row][col];
        for (int i = 0; i < row; i++) {
            parts = br.readLine().split(" ");
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(parts[j]);
            }
        }

        //print();

        while (t-- > 0) {
            expand();
            //print();
            clean();
            //print();
        }

        int result = getResult();
        System.out.println(result);
    }

    private static void clean() {
        int state  = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == -1) {
                    if (state == 0) {
                        for (int k = i - 1; k >= 1; k--) {
                            map[k][j] = map[k -1][j];
                        }
                        for (int k = 0; k < col - 1; k++) {
                            map[0][k] = map[0][k + 1];
                        }
                        for (int k = 0; k < i; k++) {
                            map[k][col - 1] = map[k + 1][col - 1];
                        }
                        for (int k = col - 1; k >= 2; k--) {
                            map[i][k] = map[i][k - 1];
                        }
                        map[i][1] = 0;

                        state++;
                    } else {
                        for (int k = i + 1; k < row - 1; k++) {
                            map[k][j] = map[k + 1][j];
                        }
                        for (int k = 0; k < col - 1; k++) {
                            map[row - 1][k] = map[row - 1][k + 1];
                        }
                        for (int k = row - 1; k > i; k--) {
                            map[k][col - 1] = map[k - 1][col - 1];
                        }
                        for (int k = col - 1; k >= 2; k--) {
                            map[i][k] = map[i][k - 1];
                        }
                        map[i][1] = 0;
                    }
                }
            }
        }
    }

    private static boolean canMove(int x, int y) {
        return x >= 0 && y >= 0 && x < row && y < col && map[x][y] != -1;
    }

    private static void expand() {
        int[][] result = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int count = 0;
                if (map[i][j] != -1 && map[i][j] != 0) {
                    if (canMove(i - 1, j)) {
                        result[i - 1][j] += map[i][j] / 5;
                        count++;
                    }
                    if (canMove(i + 1, j)) {
                        result[i + 1][j] += map[i][j] / 5;
                        count++;
                    }
                    if (canMove(i, j - 1)) {
                        result[i][j - 1] += map[i][j] / 5;
                        count++;
                    }
                    if (canMove(i, j + 1)) {
                        result[i][j + 1] += map[i][j] / 5;
                        count++;
                    }
                    result[i][j] -= (map[i][j] / 5) * count;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] += result[i][j];
            }
        }
    }

    private static int getResult() {
        int total = 0;
        for (int[] row: map) {
            for (int num: row) {
                if (num != -1) total += num;
            }
        }
        return total;
    }

    private static void print() {
        for (int[] row: map) {
            for (int num: row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

