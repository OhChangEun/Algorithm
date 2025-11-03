import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
   		List<String> list = new ArrayList<>();
		list.add(begin);	
     
        boolean hasTarget = false;
        int targetIndex = -1;
        for (int i=0; i<words.length; i++) {
       		list.add(words[i]);
            if (words[i].equals(target)) {
                hasTarget = true;
                targetIndex = i + 1;
            }
        }
        if (!hasTarget) return 0;
      
        int size = list.size();
        List<List<Integer>> graph = new ArrayList<>(); 
        for (int i=0; i<size; i++) {
           	graph.add(new ArrayList<>()); 
        }
        
        for (int i=0; i<size-1; i++) {
           	for (int j=i+1; j<size; j++) {
                if (isConvertible(list.get(i), list.get(j))) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            } 
        }
       	System.out.println(graph);
        int start = 0;
        int result = bfs(start, targetIndex, graph);
        return result;
    }
   	public int bfs(int start, int targetIndex, List<List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.size()];
        int[] distance = new int[graph.size()];
        
        visited[start] = true; 
        queue.offer(start);
        
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == targetIndex) return distance[curr];
            
            for (int next: graph.get(curr)) {
            	if (!visited[next]) {
                    visited[next] = true; 
                    distance[next] = distance[curr] + 1; 
                    queue.offer(next);
                }    
            }
        }
        
        return 0;
    } 
    
    
    public boolean isConvertible(String a, String b) {
        int diff = 0;
        for (int i=0; i<a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return diff == 1;
    }
}