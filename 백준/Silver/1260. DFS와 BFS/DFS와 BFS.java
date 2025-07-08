import java.io.*;
import java.util.*;

public class Main {
    static int[][] graph;
    static boolean[] dfsVisited;
    static boolean[] bfsVisited;

   public static void dfs(int node) {
       dfsVisited[node] = true;
       System.out.print(node + " ");
       for (int i=0; i<graph.length; i++) {
           if (graph[node][i] == 1 && !dfsVisited[i]) {
               dfs(i);
           }
       }
   }

   public static void bfs(int start) {
       Queue<Integer> queue = new LinkedList<>();
       bfsVisited[start] = true;
       queue.offer(start);

       while (!queue.isEmpty()) {
           int node = queue.poll();
           System.out.print(node + " ");

           for (int next=0; next<graph.length; next++) {
               if (graph[node][next] == 1 && !bfsVisited[next]) {
                   bfsVisited[next] = true;
                   queue.offer(next);
               }
           }
       }
   }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];
        dfsVisited = new boolean[N+1];
        bfsVisited = new boolean[N+1];

        for (int i=0; i<M; i++) {
            String[] input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]); // 시작 노드
            int e = Integer.parseInt(input[1]); // 끝 노드

            graph[s][e] = 1;
            graph[e][s] = 1;
        }
        dfs(V);
        System.out.print("\n");
        bfs(V);
    }
}
