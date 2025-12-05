import java.util.*;

class Solution {
    int result = 0;
    public int solution(int[] info, int[][] edges) {
   		int n = info.length;	
        List<List<Integer>> graph = new ArrayList<>();
       	for (int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        } 
       
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
        }
        
        int start = 0;
        List<Integer> candidates = new ArrayList<>();
       	candidates.add(start);
        
        dfs(info, graph, start, 0, 0, candidates);
        
        return result;
    }
    public void dfs(int[] info, List<List<Integer>> graph, int curr, int sheep, int wolf, List<Integer> candidates)  {
        if (info[curr] == 0) sheep++;
        else wolf++; 
        
        if (sheep <= wolf) return; 
       
        result = Math.max(result, sheep); 
        
        List<Integer> nextCandidates = new ArrayList<>(candidates); 
        nextCandidates.remove(Integer.valueOf(curr));
        
        for (int child: graph.get(curr)) {
    		nextCandidates.add(child);        
        }
        
        for (int next: nextCandidates) {
           	dfs(info, graph, next, sheep, wolf, nextCandidates); 
        }
    }
}