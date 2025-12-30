import java.util.*;

class Solution {
    List<List<Integer>> graph;
   	boolean[] visited; 
    long[] a; 
    long result = 0;
    
    public long solution(int[] a, int[][] edges) {
   		int n = a.length; 
        this.a = new long[n];
        
        long sum = 0;
        for (int i = 0; i < n; i++) {
       		this.a[i] = a[i]; 
       		sum += a[i];     
        }
        
        if (sum != 0) return -1;
        
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
           	graph.add(new ArrayList<>()); 
        }
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        visited = new boolean[n];
        dfsIterative(0);
        
        return result;
    }
    
    private void dfsIterative(int root) {   
        int[] parent = new int[a.length];
        Arrays.fill(parent, -1);
        
        List<Integer> postOrder = new ArrayList<>(); 
        Stack<Integer> stack = new Stack<>();
        
        stack.push(root); 
        visited[root] = true;
        
        while (!stack.isEmpty()) {
            int curr = stack.pop(); 
            postOrder.add(0, curr);
            
            for (int next: graph.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true; 
                    stack.push(next);
                    parent[next] = curr;
                }         
            }
        }
       
        for (int num: postOrder) {
            //System.out.println(num);
            result += Math.abs(a[num]);
            
            if (parent[num] != -1) {
                a[parent[num]] += a[num];
            }
        }
    }
}