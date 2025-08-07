class Solution {
    public int[] solution(int brown, int yellow) {
       
        // w * h = brown + yellow
       	// yellow = (w-2)(h-2) 
        int total = brown + yellow;
        for (int h = 1; h <= total/h; h++) { 
            int w = total/h;
            
            if (yellow == (w-2)*(h-2)) {
                return new int[] {w, h};
            }
        }
        
        
        
        return new int[] {0, 0};
    }
}