class Solution {
    public String solution(String s) {        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (i == 0) {
                sb.append(Character.toUpperCase(ch));
            } else {
                if (s.charAt(i - 1) == ' ') {
                    sb.append(Character.toUpperCase(ch));
                } else {
                    sb.append(Character.toLowerCase(ch));
                }
            }
        }
        
        return sb.toString();
    }
    
    private void print(Object o) {
        System.out.println(o);
    }
}