import java.util.*;

class Solution {
    int[] parent;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parent = new int[n];
        for (int i = 1; i < n; i++) {
            parent[i] = i;
        }
        
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        
        for (int[] cost: costs) {
            int u = cost[0];
            int v = cost[1];
            int c = cost[2]; 
            
            if (find(u) != find(v)) {
                union(u, v);
                answer += c;
            }
        }
        
        
        return answer;
    }
    
    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        
        return parent[x] = find(parent[x]);
    }
    
    private void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        parent[rootA] = rootB;
    }
}