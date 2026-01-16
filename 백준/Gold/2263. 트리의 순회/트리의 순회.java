import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static StringBuilder result;
    static int[] inorder;
    static int[] postorder;
    static int[] index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());


        inorder = new int[n];
        index = new int[n + 1];
        String[] parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            inorder[i] = Integer.parseInt(parts[i]);
            index[inorder[i]] = i;
        }

        postorder = new int[n];
        parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            postorder[i] = Integer.parseInt(parts[i]);
        }

        result = new StringBuilder();
        dfs(0, n - 1, 0, n - 1);

        System.out.println(result.toString());
    }

    private static void dfs(int inL, int inR, int postL, int postR) {
        if (inL > inR || postL > postR) return;

        int root = postorder[postR];
        result.append(root).append(" ");

        int rootIdx = index[root];
        int leftSize = rootIdx - inL;

        dfs(inL, rootIdx - 1, postL, postL + leftSize - 1);
        dfs(rootIdx + 1, inR, postL + leftSize, postR - 1);
    }
}