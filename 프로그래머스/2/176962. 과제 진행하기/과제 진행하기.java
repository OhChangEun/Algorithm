import java.util.*;

class Solution {
    class Task {
        String name; 
        int duration; 
        
        public Task(String name, int duration) {
            this.name = name;
            this.duration = duration;
        }
    }
    
    public String[] solution(String[][] plans) {
    
        Arrays.sort(plans, (a, b) -> a[1].compareTo(b[1]));
      
        ArrayDeque<Task> stack = new ArrayDeque<>();
        
        int idx = 0; 
        int n = plans.length;
        int currTime = 0;
        List<String> result = new ArrayList<>();
        
        while (idx < n) {
            String taskName = plans[idx][0]; 
            int startTime = toMinute(plans[idx][1]); 
            int duration = Integer.parseInt(plans[idx][2]);
           
            if (stack.isEmpty()) {
                currTime = startTime;
                stack.push(new Task(taskName, duration)); 
                idx++;
                continue;
            } 
            
            Task prev = stack.pop();
            if (currTime + prev.duration <= startTime) {
                result.add(prev.name);
                currTime += prev.duration; 
                
                while (!stack.isEmpty()) {
                    Task next = stack.peek();
                    if (currTime + next.duration <= startTime) {
                        result.add(stack.pop().name);
                        currTime += next.duration;
                    } else {
                        int remain = startTime - currTime; 
                        if (remain > 0) {
                            next.duration -= remain; 
                            currTime += remain;
                        }
                        
                        break;
                    }
                }
                
                currTime = startTime; 
                stack.push(new Task(taskName, duration)); 
            } else {
                int remaining = prev.duration - (startTime - currTime);
                stack.push(new Task(prev.name, remaining)); 
                currTime = startTime;
                stack.push(new Task(taskName, duration)); 
            }
           
            idx++;
        }
        
        while (!stack.isEmpty()) {
            Task left = stack.pop();
            result.add(left.name);
        }
          
        String[] answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
    
    public int toMinute(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        
        return hour * 60 + minute;
    }
}