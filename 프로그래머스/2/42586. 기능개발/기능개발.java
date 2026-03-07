import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        int n = progresses.length; 
       
        int[] workTime = new int[n];
        for (int i = 0; i < n; i++) {
           	workTime[i] = ((100 - progresses[i]) + speeds[i] - 1) / speeds[i];
        }
      
        ArrayDeque<Integer> queue = new ArrayDeque<>();
       
        List<Integer> list = new ArrayList<>();
        for (int time: workTime) {
            if (!queue.isEmpty()) {
                if (queue.peek() < time) {
                    list.add(queue.size());
                    queue.clear();
                } 
            }
           	queue.offer(time); 
        }
        list.add(queue.size());
       
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        
        return result;
    }
}