class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0; 
        int start = 1;
        int range = 2 * w + 1;
        
        for (int station: stations) {
            int left = station - w;
           
            int gap = left - start;
            if (gap > 0) {
            	answer += (gap + range - 1) / range;
            }
            start = station + w + 1;
        }

        if (start <= n) {
           	int gap = n - start + 1;
            answer += (gap + range - 1) / range;
        }

        return answer;
    }
}