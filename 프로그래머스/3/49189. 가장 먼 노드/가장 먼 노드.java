import java.util.*;

class Solution {
    public void bfs(int start, List<List<Integer>> graph, boolean[] visited, int[] distance) {
   		Queue<Integer> queue = new LinkedList<>();	
        queue.offer(start);
        visited[start] = true;
      	distance[1] = 1; 
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
       		// System.out.print(distance[curr] + " "); 
            for (int next: graph.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                    distance[next] = distance[curr] + 1;
                }
            } 
            /*
            for (int i=0; i < graph.length; i++) {
                if (graph[curr][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                    distance[i] = distance[curr] + 1;
                }
            }
            */
        }
    } 
    
    public int solution(int n, int[][] edge) {
      
        /*
        int[][] graph = new int[n+1][n+1];
        
        for (int i=0; i<edge.length; i++) {
       		int start = edge[i][0]; 
       		int end = edge[i][1];
            
            graph[start][end] = 1;
            graph[end][start] = 1;
        }
        */
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
       
        for (int[] e: edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        
        
    	boolean[] visited = new boolean[n+1]; 
       	int[] distance = new int[n+1];
        for (int i=1; i<=n; i++) {
            if (!visited[i]) {
                bfs(1, graph, visited, distance);
            }
        }
       
        int count = 0;
        int max = distance[0];
        for (int d: distance) {
            if (max < d) 
       			max = d;        
        }
        
        for (int d: distance) {
            if (max == d) {
                count++;
            }
        }
        
        return count;
    }
}