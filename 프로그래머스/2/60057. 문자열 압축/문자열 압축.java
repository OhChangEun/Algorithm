class Solution {
    public int solution(String s) {
        int n = s.length();
        int answer = n; // 압축 안 했을 때가 최대
        
        // 자르는 단위
        for (int size = 1; size <= n / 2; size++) {
            StringBuilder compressed = new StringBuilder();
            
            String prev = s.substring(0, size);
            int count = 1;
            
            // size 단위로 문자열 순회
            for (int i = size; i <= n; i += size) {
                String curr;
                
                if (i + size <= n) {
                    curr = s.substring(i, i + size);
                } else {
                    curr = s.substring(i); // 남은 부분
                }
                
                if (prev.equals(curr)) {
                    count++;
                } else {
                    if (count > 1) {
                        compressed.append(count);
                    }
                    compressed.append(prev);
                    
                    prev = curr;
                    count = 1;
                }
            }
            
            // 마지막 처리
            if (count > 1) {
                compressed.append(count);
            }
            compressed.append(prev);
            
            answer = Math.min(answer, compressed.length());
        }
        
        return answer;
    }
}
