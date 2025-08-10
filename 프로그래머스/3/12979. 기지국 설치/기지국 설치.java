class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int range = 2*w + 1;
       	int start = 1; // 현재 아파트 번호(1-based)
        
        for (int station: stations) { // 모든 기지국에 대해 
           	int left = station - w; // 기지국 왼쪽 좌표 
            if (start < left) {
                int gap = left-start;
                answer += (gap + range - 1) / range; // 올림 계산 
            }
            start = station + w + 1; // 다음 지점(기지국 오른쪽 좌표)으로 이동 
        }
       
        // 기지국이 중간에서 끝나는 경우
        if (start <= n) {
            int gap = n - start + 1;
            answer += (gap + range - 1) / range;
        }
        
        return answer;
    }
}