import java.util.*;

class Solution {
    Map<String, PriorityQueue<String>> airportMap;
   	List<String> result;	
    
    public String[] solution(String[][] tickets) {
    	airportMap = new HashMap<>();
        result = new ArrayList<>();
        
        for (String[] ticket: tickets) {
            String from = ticket[0];
            String to = ticket[1];
            
            airportMap.putIfAbsent(from, new PriorityQueue<>());
            airportMap.get(from).add(to);
        }
        // System.out.println(airportMap);
       
        String start = "ICN";
        dfs(start); 
        
        return result.toArray(new String[0]);
    }
    public void dfs(String airport) {
       	while (airportMap.containsKey(airport) && !airportMap.get(airport).isEmpty()) {
            String next = airportMap.get(airport).poll();
            dfs(next);
        } 
        result.add(0, airport);
    }
}