import java.util.*;

class Solution {
    class Job implements Comparable<Job> { 
        int idx; 
        int requireTime; 
        int runTime; 
        
        public Job(int idx, int requireTime, int runTime) {
            this.idx = idx;
            this.requireTime = requireTime;
            this.runTime = runTime;
        }
        
        @Override
        public int compareTo(Job other) {
            if (this.runTime != other.runTime) return this.runTime - other.runTime; 
            if (this.requireTime != other.requireTime) return this.requireTime - other.requireTime; 
            return this.idx - other.idx; 
        }
    }
    public int solution(int[][] jobs) {
        int totalTime = 0;
        
        List<Job> jobList = new ArrayList<>();
        for (int i = 0; i < jobs.length; i++) {
            int requireTime = jobs[i][0];
            int runTime = jobs[i][1];
            
            totalTime += runTime;
            jobList.add(new Job(i, requireTime, runTime));
        }
        
        // 작업 요청 시간 순으로 리스트 정렬
        Collections.sort(jobList, (a, b) -> a.requireTime - b.requireTime);

        int result = 0;
        
        int now = 0; 
        int idx = 0; 
        int done = 0; 
        PriorityQueue<Job> pq = new PriorityQueue<>(); // 준비 큐 
        while (done < jobs.length) {
            // 현재 시간에 맞는 요청시간을 가진 작업들은 다 pq에 넣기 
            while (idx < jobs.length && jobList.get(idx).requireTime <= now) {
                pq.offer(jobList.get(idx));
                idx++;
            }
            
            // 현재 시간에 있는 최상단 pq 빼기 -> 작업 시작 
            if (!pq.isEmpty()) {
                Job curr = pq.poll();
                
                now += curr.runTime; 
                int turnaroundTime = now - curr.requireTime; 
                result += turnaroundTime; 
                done++;
            } else {
                now = jobList.get(idx).requireTime;
            }
        }
        
        return result / jobs.length;
    }
}