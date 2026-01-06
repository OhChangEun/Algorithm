import java.util.*;

class Solution {
    int[][] dp;
    List<List<Integer>> graph; 
    boolean[] visited;
    
    public int solution(int n, int[][] lighthouse) {
        dp = new int[n + 1][2]; 
        visited = new boolean[n + 1];
        graph = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] light: lighthouse) {
            int u = light[0];
            int v = light[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
       
        int root = 1;
        dfs(root);
        
        return Math.min(dp[root][0], dp[root][1]);
    }

    private void dfs(int curr) {
        visited[curr] = true; 
        
        dp[curr][0] = 0; 
        dp[curr][1] = 1; 
        
        for (int next: graph.get(curr)) {
            if (!visited[next]) {
                dfs(next);
                
                dp[curr][1] += Math.min(dp[next][0], dp[next][1]);
                dp[curr][0] += dp[next][1]; 
            }
        }
    }
}