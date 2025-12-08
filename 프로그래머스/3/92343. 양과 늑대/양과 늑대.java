import java.util.*;

class Solution {
    int maxSheep = 0;
   
    public int solution(int[] info, int[][] edges) {
   		int n = info.length;	
        List<List<Integer>> graph = new ArrayList<>();
       	for (int i=0; i<n; i++) {
           	graph.add(new ArrayList<>()); 
        }
        
        for (int[] edge: edges) {
            int parent = edge[0];
            int child = edge[1];
            graph.get(parent).add(child);
        }
      
        int start = 0;
        List<Integer> candidates = new ArrayList<>();
        candidates.add(start);
       
        dfs(info, graph, start, 0, 0, candidates);
        
   		return maxSheep; 
    }
   
    public void dfs (int[] info, List<List<Integer>> graph, int curr, int sheep, int wolf, List<Integer> candidates) {
       	if (info[curr] == 0) sheep++;
        else wolf++;
        
        if (sheep <= wolf) return; 
       	maxSheep = Math.max(maxSheep, sheep);
        
        List<Integer> newCandidates = new ArrayList<>(candidates);
        newCandidates.remove(Integer.valueOf(curr));
      
        for (int child: graph.get(curr)) {
            newCandidates.add(child);
        }
        
        for (int next: newCandidates) {
            dfs(info, graph, next, sheep, wolf, newCandidates);
        }
    }
}