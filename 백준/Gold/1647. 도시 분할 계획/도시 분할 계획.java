import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] houses;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");

        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);

        houses = new int[m][3];
        for (int i = 0 ; i < m; i++) {
            parts = br.readLine().split(" ");
            houses[i][0] = Integer.parseInt(parts[0]);
            houses[i][1] = Integer.parseInt(parts[1]);
            houses[i][2] = Integer.parseInt(parts[2]);
        }

        Arrays.sort(houses, (a, b) -> {
            if (a[2] != b[2]) return a[2] - b[2];
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        int result = 0;
        int max = 0;
        for (int[] house: houses) {
            int u = house[0];
            int v = house[1];
            int w = house[2];

            if (find(u) != find(v)) {
                union(u, v);
                result += w;
                max = Math.max(max, w);
                // System.out.println("start: " + u + " end: " + v + " w: " + w);
            }
        }

        System.out.println(result - max);

        /*
        for (int[] house: houses) {
            for (int num: house) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        */
    }

    private static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b); 
        
        parent[b] = a;
    }
}