class Solution {
    public int solution(int n, String control) {        
        for (char ch: control.toCharArray()) {
           	switch (ch) {
                case 'w': 
                    n++;
                    break;
                case 's':
                    n--;
                    break;
                case 'd': 
                    n += 10;
                    break;
                case 'a':
                    n -= 10;
                    break;
                default: 
                    break;
            } 
        }
        return n;
    }
}