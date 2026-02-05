class Solution {
    public int solution(int n, int[] stations, int w) {
        int cnt = 0; 
        int coverRange = 2 * w + 1; 
        
        int left = 0; 
        for (int station: stations) {
            station--; // 0-based
            int right = station - w - 1; 
            
            // System.out.println(left + " " + right);
            int need = (right - left + 1 + coverRange - 1) / coverRange; // 기지국 설치 올림 계산 
            cnt += need; 
            left = station + w + 1; 
            // System.out.println(left + " " + right);
        }
        
        // 남은 범위 기지국 설치
        if (left < n) {
            int need = (n - 1 - left + 1 + coverRange - 1) / coverRange; 
            cnt += need; 
        }

        return cnt;
    }
}