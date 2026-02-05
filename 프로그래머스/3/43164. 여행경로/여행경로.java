import java.util.*;

class Solution {
    final String START = "ICN";
    Map<String, PriorityQueue<String>> graph; 
    List<String> pathList; 
    
    public String[] solution(String[][] tickets) {
        int n = tickets.length; 
        
        graph = new HashMap<>();
        for (String[] ticket: tickets) {
            String from = ticket[0];
            String to = ticket[1]; 
            
            graph.putIfAbsent(from, new PriorityQueue<>()); 
            graph.get(from).add(to);
        }
        
        pathList = new ArrayList<>();
        dfs(START);
       
        Collections.reverse(pathList); // 경로 뒤집기 
        
        return pathList.toArray(new String[0]);
    }
    
    private void dfs(String curr) {
        
        PriorityQueue<String> pq = graph.get(curr);
        while (pq != null && !pq.isEmpty()) {
        	String next = pq.poll();
        	dfs(next);    
        }
        
        pathList.add(curr); // 막다른 길에 다다르면 도착했다는 의미이므로 경로 역순으로 넣기   
    }
}