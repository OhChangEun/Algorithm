import java.util.*;

class Solution {
    public String[] solution(String[] record) {
       
        Map<String, String> userMap = new HashMap<>(); 
        
        for (String r: record) {
            String[] parts = r.split(" ");
            String cmd = parts[0];
            String userId = parts[1];
            if (cmd.equals("Enter") || cmd.equals("Change")) {
            	String userName = parts[2]; 
            	userMap.put(userId, userName); 
            }
        }
        
        List<String> msgList = new ArrayList<>();
        for (String r: record) {
            String[] parts = r.split(" ");
            String cmd = parts[0];
            String userId = parts[1];
            
            String userName = userMap.get(userId);
            if (cmd.equals("Enter")) {
            	msgList.add(userName + "님이 들어왔습니다.");
            } else if (cmd.equals("Leave")) {
                msgList.add(userName + "님이 나갔습니다.");
            }  
        }
        
        return msgList.toArray(new String[0]);
    }
}