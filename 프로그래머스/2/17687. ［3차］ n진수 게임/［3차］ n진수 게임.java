class Solution {
    public String solution(int n, int t, int m, int p) {
        
        int num = 0;
        StringBuilder sequence = new StringBuilder();
        while (sequence.length() < t * m) {
            sequence.append(Integer.toString(num, n).toUpperCase()); // n 진수 변환
            num++; 
        }
        
        StringBuilder result = new StringBuilder();
        for (int i=0; i<t; i++) {
       		result.append(sequence.charAt((p-1)+(i*m))); // p-1(1-based), i번째 주기     
        }
       
       	String answer = result.toString(); 
       
        return answer;
    }
}