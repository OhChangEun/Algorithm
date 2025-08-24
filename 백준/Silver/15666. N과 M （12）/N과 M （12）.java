import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M;
    static int[] inputs;
    static int[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");

        N = Integer.parseInt(parts[0]);
        M = Integer.parseInt(parts[1]);

        inputs = new int[N];
        selected = new int[M];
        String[] inputParts = br.readLine().split(" ");
        for (int i=0; i<N; i++) {
            inputs[i] = Integer.parseInt(inputParts[i]);
        }

        // 1 7 9 9
        Arrays.sort(inputs);
        dfs(0, 0);

        // 1 1
        // 1 7
        // 1 9
        // 7 7
        // 7 9
        // 9 9
    }
    public static void dfs(int curr, int depth) {
        if (depth == M) {
            for (int num: selected) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        int prev = -1;
        for (int i=curr; i<N; i++) { //
            if (prev != inputs[i]) {
                selected[depth] = inputs[i];
                prev = inputs[i];
                dfs(i, depth + 1);
            }
        }
    }
}
