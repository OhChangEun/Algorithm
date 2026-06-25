class Solution {
    public String[] solution(String[] s) {
        int n = s.length; 
        
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = move110(s[i]);
        }
        
        return result;
    }
    
    private String move110(String str) {
        int cnt = 0;

        StringBuilder sb = new StringBuilder(); 
        for (char ch: str.toCharArray()) {
            sb.append(ch);
            
            int len = sb.length();
            if (len >= 3) {
                if (sb.charAt(len - 3) == '1' && sb.charAt(len - 2) == '1' && sb.charAt(len - 1) == '0') {
                    sb.delete(len - 3, len);
                    cnt++;
                }
            }
        }
        
        // print(sb.lastIndexOf("0"));
        int zeroIdx = sb.lastIndexOf("0");
        
        StringBuilder result = new StringBuilder(); 
        result.append(sb.substring(0, zeroIdx + 1));
        result.append("110".repeat(cnt));
        result.append(sb.substring(zeroIdx + 1));
        
        return result.toString();
    }
    
    private void print(Object o) {
        System.out.println(o);
    }
}