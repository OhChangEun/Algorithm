class Solution {
    private int[][] arr;
    private int zeroCnt = 0; 
    private int oneCnt = 0;
    
    public int[] solution(int[][] arr) {
        this.arr = arr; 
       
        int len = arr.length;
        dfs(0, len - 1, 0, len - 1);
        
        return new int[] {zeroCnt, oneCnt};
    }
    
    private void dfs(int minX, int maxX, int minY, int maxY) {
        if (check(minX, maxX, minY, maxY) == 0) {
            zeroCnt++;
        } else if (check(minX, maxX, minY, maxY) == 1){
            oneCnt++;
        } else {
            int midX = (minX + maxX) / 2; 
            int midY = (minY + maxY) / 2; 
            
            dfs(minX, midX, minY, midY);
            dfs(midX + 1, maxX, minY, midY);
            dfs(minX, midX, midY + 1, maxY);
            dfs(midX + 1, maxX, midY + 1, maxY);
        }   
    }
    
    // 해당 범위가 모두 같은 숫자라면 숫자 반환, 아니라면 -1 반환 
    private int check(int minX, int maxX, int minY, int maxY) {
        int first = arr[minY][minX];
        
        boolean allSame = true;
        for (int i = minY; i <= maxY; i++) {
            for (int j = minX; j <= maxX; j++) {
                if (first != arr[i][j]) 
                    allSame = false;
            }
        } 
        
        if (!allSame) return -1;
        return first;
    }
}