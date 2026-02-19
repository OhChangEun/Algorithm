import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int k = Integer.parseInt(parts[2]);

        int[] myCards = new int[m];
        parts = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            int card = Integer.parseInt(parts[i]);
            myCards[i] = card;
        }

        int[] minsuCards = new int[k];
        parts = br.readLine().split(" ");
        for (int i = 0; i < k; i++) {
            int card = Integer.parseInt(parts[i]);
            minsuCards[i] = card;
        }

        Arrays.sort(myCards);

        parent = new int[m + 1];
        for (int i = 0; i <= m; i++) {
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int minsu: minsuCards) {
            int left = 0;
            int right = m - 1;
            int idx = m;

            while (left <= right) {
                int mid = (left + right) / 2;

                int myCard = myCards[mid];
                if (minsu < myCard) {
                    idx = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            idx = find(idx);

            sb.append(myCards[idx]).append("\n");

            union(idx, idx + 1);
        }

        System.out.println(sb.toString());
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        parent[rootA] = rootB;
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}
