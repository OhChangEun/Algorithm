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

        for (String[] plan : plans) {
            int startTime = toMinute(plan[1]);
            int duration = Integer.parseInt(plan[2]);
            int gap = startTime - prevTime;

            // gap 시간만큼 스택 소진
            while (!stack.isEmpty() && gap > 0) {
                Task top = stack.peek();
                if (top.duration <= gap) {
                    gap -= top.duration;
                    result.add(stack.pop().name);
                } else {
                    top.duration -= gap;
                    gap = 0;
                }
            }

            stack.push(new Task(plan[0], duration));
            prevTime = startTime;
        }

        // 남은 task 처리
        while (!stack.isEmpty()) {
            result.add(stack.pop().name);
        }

        return result.toArray(new String[0]);
    }
    
    public int toMinute(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        
        return hour * 60 + minute;
    }
}