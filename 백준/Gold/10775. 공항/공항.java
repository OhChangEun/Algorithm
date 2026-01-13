import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int gateNum = Integer.parseInt(br.readLine());
        int planeNum = Integer.parseInt(br.readLine());

        parent = new int[gateNum + 1];
        for (int i = 1; i <= gateNum; i++) {
            parent[i] = i;
        }

        int count = 0;
        for (int i = 1; i <= planeNum; i++) {
            int plane = Integer.parseInt(br.readLine());

            int gate = find(plane);
            if (gate == 0){
                break;
            }

            parent[gate] = gate - 1;
            count++;
        }
        
        System.out.println(count);
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }
}