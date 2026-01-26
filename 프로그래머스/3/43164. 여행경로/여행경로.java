import java.util.*;

class Solution {
    final String startAirport = "ICN";
    
    int n; 
    Map<String, PriorityQueue<String>> graph;
    List<String> pathList;
    public String[] solution(String[][] tickets) {
        this.n = tickets.length; 
        pathList = new ArrayList<>();
        
        graph = new HashMap<>();
        for (String[] ticket: tickets) {
            String start = ticket[0];
            String end = ticket[1]; 
            
            graph.putIfAbsent(start, new PriorityQueue<>());
            graph.get(start).add(end);
        }
        
        // System.out.println(graph);
        
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