class Solution {
    public int solution(int[] stones, int k) {
        
        int left = 0;
        int right = 200_000_000; 
        
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (canGo(stones.clone(), mid, k)) {
                left = mid + 1;
                answer = mid; 
            } else {
                right = mid - 1;
            }
        }
        
        return answer + 1;
    }
    
    // 배열에 num을 다 빼고,
    // 연속된 0이 k보다 작으면 넘어갈 수 있음 
    private boolean canGo(int[] arr, int num, int k) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.max(arr[i] - num, 0); 
        }
        
        int left = 0; 
        int maxLen = 0;
        for (int right = 0; right < arr.length; right++) {
            if (arr[right] == 0) {
                int len = right - left + 1;
                maxLen = Math.max(maxLen, len);
            } else {
                left = right + 1;
            }
        }
        
        if (maxLen < k) return true;
        return false;
    }
}