import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
   		List<List<Integer>> winGraph = new ArrayList<>();	
   		List<List<Integer>> loseGraph = new ArrayList<>();	
      
        for (int i=0; i<=n; i++) {
           	winGraph.add(new ArrayList<>()); 
           	loseGraph.add(new ArrayList<>()); 
        }
        
        for (int[] result: results) {
           	int u = result[0]; 
           	int v = result[1]; 
        	winGraph.get(u).add(v); 
        	loseGraph.get(v).add(u); 
        } 
		
        int result = 0;
        for (int i=1; i<=n; i++) {
            int winCount = bfs(n, winGraph, i);
            int loseCount = bfs(n, loseGraph, i);
           
            int totalCount = winCount + loseCount;
            if (totalCount == n - 1) 
                result++;
        }
        
        return result;
    }
    public int bfs(int n, List<List<Integer>> graph, int start) {
        Queue<Integer> queue = new LinkedList<>(); 
   		boolean[] visited = new boolean[n + 1]; 
        
        queue.offer(start);
   		visited[start] = true; 
 
        // bfs 시 방문하는 노드 수
        int count = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next: graph.get(curr)) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                    count++;
                }
            }
        }
        
        return count;
    }
}