import java.util.*;

class Solution {
    int[] parent; 
    
    public int solution(int n, int[][] costs) {
        
        // 부모 초기화 
       	parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; 
        }
        
        int totalCost = 0; 
        
        // 거리 기준 정렬 
       	Arrays.sort(costs, (a, b) -> a[2] - b[2]); 
        for (int[] cost: costs) {
            int u = cost[0];
            int v = cost[1]; 
            int c = cost[2]; 
            
            if (find(u) != find(v)) {
                union(u, v);
            	totalCost += c; 
            }
        }
        
        return totalCost; 
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
        
        if (rootA != rootB) {
            parent[rootA] = rootB; 
        }
    }
}