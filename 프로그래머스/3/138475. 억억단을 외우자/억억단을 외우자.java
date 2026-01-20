class Solution {
    public int[] solution(int e, int[] starts) {
        
        int[] count = new int[e + 1];
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                count[j]++;
            }
        }
        
        // best[i]: i ~ e 구간 중 최대 약수 개수를 가지는 수 
        int[] best = new int[e + 1]; 
        best[e] = e;
        
        for (int i = e - 1; i >= 1; i--) {
            if (count[i] >= count[best[i + 1]]) {
                best[i] = i;
            } else {
                best[i] = best[i + 1]; 
            }
        }
        
        int n = starts.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = best[starts[i]];
        }
        
        return answer;
    }
}