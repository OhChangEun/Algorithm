class Solution {
    public int[] solution(int n, int s) {
		if (n > s) return new int[] {-1};
        // n == 3, 
        // 8 / 3 = 2...2
        // => {2, 3, 3} // 18
       	// 1) s를 n으로 나누어서 n개의 크기를 가지는 배열에 그 몫을 분배한다. 
        // 2) s를 n으로 나눈 나머지의 값에서 1씩 빼면서 그 배열에 분배한다. 
        // 3) 오름차순으로 정렬하여 값을 반환한다. 
        
        int quotient = s / n; 
        int remainder = s % n;
      
        int[] result = new int[n];
        for (int i=0; i<n; i++) {
            result[i] = quotient;
        }
        
        for (int i=0; i<remainder; i++) {
            result[n-1-i] += 1;
        }
        
        return result;
    }
}