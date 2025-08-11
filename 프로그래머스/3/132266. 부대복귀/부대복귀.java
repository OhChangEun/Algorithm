import java.util.*;

class Solution {
    
    public void bfs(int start, List<List<Integer>> graph, int[] distance) {
       	Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        distance[start] = 0;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            // System.out.print(curr + " ");
            for (int next: graph.get(curr)) {
                if (distance[next] == -1) {
                    distance[next] = distance[curr] + 1;
                    queue.offer(next);
                }
            }
        }
    }
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = {};
       
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] road: roads) {
            int a = road[0];
            int b = road[1];
           
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        // System.out.println(graph);
      
        int[] distance = new int[n+1];
        Arrays.fill(distance, -1);
        
        bfs(destination, graph, distance);
       
        answer = new int[sources.length];
       	for (int i=0; i<sources.length; i++) {
            answer[i] = distance[sources[i]];
        } 
                
        return answer;
    }
}