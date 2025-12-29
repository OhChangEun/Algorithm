class Solution {
    class Time {
        long start, end; 
        public Time(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }
    public int solution(String[] lines) {
        int n = lines.length;
       	Time[] times = new Time[n];
        
        for (int i = 0; i < n; i++) {
           	String[] parts = lines[i].split(" ");
        	
            String S = parts[1]; // 응답 완료 시간 
        	String T = parts[2]; // 처리 시간
            
            parts = S.split(":");
        	long hh = Integer.parseInt(parts[0]) * 3600_000L;
        	long mm = Integer.parseInt(parts[1]) * 60_000L;
        	long ss = (long)(Double.parseDouble(parts[2]) * 1000);
            
            long executionTime = (long)(Double.parseDouble(T.substring(0, T.length() - 1)) * 1000);
            // System.out.println(executionTime);
            
            long endTime = hh + mm + ss;
          	long startTime = endTime - executionTime + 1;
       
            times[i] = new Time(startTime, endTime);
        }
        
        int maxCount = 0;
       	for (int i = 0; i < n; i++) {
            long startPoint = times[i].end; 
            long endPoint = startPoint + 999;
            
        	int count = 0;
            for (int j = 0; j < n; j++) {
                if (times[j].start <= endPoint && times[j].end >= startPoint) {
                    count++;
                }
            }
            
            maxCount = Math.max(maxCount, count);
        } 
        
        return maxCount;
    }
}