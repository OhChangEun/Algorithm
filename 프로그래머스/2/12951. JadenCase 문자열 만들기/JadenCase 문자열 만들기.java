class Solution {
    public String solution(String s) {
        String answer = "";
       
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        
        for (char ch: s.toCharArray()) {
            if (ch == ' ') {
           		sb.append(' ');
                isFirst = true;
            } else if (isFirst) {
                sb.append(Character.toUpperCase(ch)); 
                isFirst = false;
            } else {
                sb.append(Character.toLowerCase(ch)); 
            }
        }
        
        return sb.toString();
    }
}