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
        List<String> result = new ArrayList<>();
        int prevTime = 0;
        
        for (String[] plan: plans) {
            String taskName = plan[0]; 
            
            int startTime = toMinute(plan[1]);
            int duration = Integer.parseInt(plan[2]);
           
            // 작업할 수 있는 시간 
            int spareTime = startTime - prevTime; 
           
            // 해야할 작업들이 있고, 작업할 시간도 남아있다면 
            while (!stack.isEmpty() && spareTime > 0) {
                Task waiting = stack.peek(); // 대기 중인 작업에 대해서 
                if (waiting.duration <= spareTime) { // 작업 시간 내에 작업을 끝낼 수 있다면 
                    spareTime -= waiting.duration; // 작업 진행하고 
                    result.add(stack.pop().name); // 결과 출력 
                } else {
                    waiting.duration -= spareTime; // 최대한 작업을 진행하고 대기 시간 기록
                    spareTime = 0; // 더이상 작업진행 불가 
                }
            }
            
            stack.push(new Task(taskName, duration));
            prevTime = startTime; 
        }
        
        while (!stack.isEmpty()) {
            result.add(stack.pop().name);
        }
       
        int n = result.size();
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
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