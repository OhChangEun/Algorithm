class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = {};
      
        int size = (int)(right - left + 1);
       	answer = new int[size]; 
        
        for (int i=0; i<size; i++) {
            long pos = left + i; 
            int row = (int)(pos / n);
            int col = (int)(pos % n);
            answer[i] = Math.max(row, col) + 1;
        }
        
        return answer;
    }
}