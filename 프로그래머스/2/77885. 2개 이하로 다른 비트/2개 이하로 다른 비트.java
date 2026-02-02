class Solution {
    public long[] solution(long[] numbers) {
        int n = numbers.length; 
        long[] answer = new long[n];
       	
        for (int i = 0; i < n; i++) {
       		if (numbers[i] % 2 == 0) { // 짝수면 
                answer[i] = numbers[i] + 1;
            } else { // 홀수면 
               	// 최대한 오른쪽(자리수가 낮은)에서 0을 1로 바꾸고, 그 오른쪽(바로 다음 낮은 자릿수)에 1을 0으로 바꾼다.
                Long next = findNext(numbers[i]);
                answer[i] = next; 
            }  
        } 
        
        return answer;
    }
    
    private Long findNext(Long num) {
        char[] charArr = ("0" + Long.toBinaryString(num)).toCharArray();
        int len = charArr.length;
        
        for (int i = len - 2; i >= 0; i--) {
            char ch = charArr[i];
            if (ch == '0') {
               	charArr[i] = '1'; 
               	charArr[i + 1] = '0'; 
                break;
            }
        }
        
        return Long.parseLong(new String(charArr), 2);
    }
}