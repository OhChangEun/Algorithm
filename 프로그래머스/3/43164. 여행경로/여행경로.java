import java.util.*;

class Solution {
    List<String> answer = new ArrayList<>();
    Map<String, List<String>> flightsMap = new HashMap<>();
   	int ticketCount = 0; 
    
    public String[] solution(String[][] tickets) {
   		ticketCount = tickets.length; 
        
        for (String[] ticket: tickets) {
            flightsMap.putIfAbsent(ticket[0], new ArrayList<>()); // 출발지 
       		flightsMap.get(ticket[0]).add(ticket[1]); // 목적지 리스트  
        }
        
        // 각 목적지 오름차순 정렬
        for (String key: flightsMap.keySet()) {
            Collections.sort(flightsMap.get(key));
        }
        
        List<String> path = new ArrayList<>();
        path.add("ICN");
        dfs("ICN", path);
       
        return answer.toArray(new String[0]);
    }
    public boolean dfs(String start, List<String> path) {
        if (path.size() == ticketCount + 1) {
            answer = new ArrayList<>(path);
            return true;
        }
        
        if (!flightsMap.containsKey(start)) return false;
       
        List<String> destList = flightsMap.get(start);
        for (int i=0; i<destList.size(); i++) {
           	String next = destList.get(i); 
            destList.remove(i);
            path.add(next);
            
            if (dfs(next, path)) return true;
       	
            path.remove(path.size() - 1); // 최근에 추가한 path 삭제
            destList.add(i, next); // 다시 복구 
        }
        return false;
    }
}