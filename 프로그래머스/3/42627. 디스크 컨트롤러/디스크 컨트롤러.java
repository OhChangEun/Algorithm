import java.util.*;

class Solution {
    class Task implements Comparable<Task>{
        int idx;
        int start; 
        int duration; 
        
        public Task(int idx, int start, int duration) {
            this.idx = idx;
            this.start = start; 
            this.duration = duration; 
        }
        
        @Override
        public int compareTo(Task other) {
            if (this.duration != other.duration) 
                return this.duration - other.duration;
            if (this.start != other.start) 
                return this.start - other.start;
            return this.idx - other.idx;
        }
    } 
    
    public int solution(int[][] jobs) {
        int n = jobs.length;  
        
        List<Task> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = jobs[i][0];
            int duration = jobs[i][1]; 
        
            list.add(new Task(i, start, duration));
        }
        
        list.sort((a, b) -> a.start - b.start);
        
        int turnarroundTime = 0;
        int currTime = 0; 
        int done = 0; 
        int idx = 0;
        PriorityQueue<Task> pq = new PriorityQueue<>();
        while (done < n) {
            // 현재 시간에 진행할 수 있는 모든 작업을 빼서 
            while (idx < n && list.get(idx).start <= currTime) {
                pq.offer(list.get(idx));
                idx++;
            }
            
            // 그 작업 중 가장 소요시간이 짧은 작업 진행 
            if (!pq.isEmpty()) {
                Task currTask = pq.poll();
                currTime += currTask.duration; 
                
                turnarroundTime += currTime - currTask.start;
                done++;
            } else {
                currTime = list.get(idx).start;
            }
        }
         
        
        return turnarroundTime / n;
    }
    
    private void print(Object o) {
        System.out.println(o);   
    }
}