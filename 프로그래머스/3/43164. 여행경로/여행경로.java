import java.util.*;

class Solution {
    final String startAirport = "ICN";
    List<String> pathList; 
    Map<String, PriorityQueue<String>> graph;
    
    public String[] solution(String[][] tickets) {
        
        graph = new HashMap<>();
        for (String[] ticket: tickets) {
            String from = ticket[0];
            String to = ticket[1];
            
            graph.putIfAbsent(from, new PriorityQueue<>());
            graph.get(from).add(to);
        }
        
        pathList = new ArrayList<>(); 
        dfs(startAirport);
        
        Collections.reverse(pathList);
        return pathList.toArray(new String[0]);
    }
    
    private void dfs(String curr) {
        PriorityQueue<String> pq = graph.get(curr);

        while (pq != null && !pq.isEmpty()) {
            String next = pq.poll();
            dfs(next);
        }
        
        pathList.add(curr);      
    }
}