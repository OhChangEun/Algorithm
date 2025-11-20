import java.util.*;

class Solution {
    int[] parent;
    public int solution(int n, int[][] costs) {
       	parent = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
        // System.out.println(parent);
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        
        int totalCost = 0; 
        int edgeCount = 0;
        for (int[] cost: costs) {
			int a = cost[0];
			int b = cost[1];
			int edgeCost = cost[2];
            
            if (find(a) != find(b)) {
                union(a, b);
                totalCost += edgeCost; 
               	edgeCount++;
                
                if (edgeCount == n-1) break;
            }
        }
        
        return totalCost;
    }
    public int find(int x) {
        if (x == parent[x]) return x; 
        return parent[x] = find(parent[x]);
    }
    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA < rootB) parent[rootB] = rootA;
        else parent[rootA] = rootB;
    }
}