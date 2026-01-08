import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board = new int[9][9];
    static List<int[]> blanks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        blanks = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < 9; j++) {
                int num = input[j] - '0';
                board[i][j] = num;

                if (num == 0) {
                    blanks.add(new int[] {i, j});
                }
            }
        }

        dfs(0);
    }

    private static boolean dfs(int depth) {
        if (depth == blanks.size()) {
            print();
            return true;
        }

        int[] pos = blanks.get(depth);
        int row = pos[0];
        int col = pos[1];

        for (int num = 1; num <= 9; num++) {
            if (isPossible(row, col, num)) {
                board[row][col] = num;
                if (dfs(depth + 1)) return true;
                board[row][col] = 0;
            }
        }
        
        return false;
    }

    private static boolean isPossible(int row, int col, int num) {
        // 열 확인
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) return false;
        }

        // 행 확인
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == num) return false;
        }

        // 3 * 3 확인
        int y = (row / 3) * 3;
        int x = (col / 3) * 3;
        for (int i = y; i < y + 3; i++) {
            for (int j = x; j < x + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }

        return true;
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int[] row: board) {
            for (int num: row) {
                sb.append(num);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}