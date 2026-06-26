class Solution {
    public int[] solution(long[] numbers) {
        int n = numbers.length; 
        
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = isBinaryTree(numbers[i]);
        }
        return answer;
    }
    
    // 1
    
    // 2~7
    //  1
    // 1 1
    
    // 8~127
    //    1
    //  1   1
    // 1 1 1 1
    // 111 1 111
    
    // 58
    //    1
    //  1   1
    // 0 1 0 0
    
    // 0111010
    // 32 + 16 + 8 + 2 => 58
    public int isBinaryTree(long number) {
        String str = Long.toBinaryString(number);
        
        int len = 1; 
        while (str.length() > len) {
            len  = len * 2 + 1;
        }
                
        // 자리수를 1 3 7 15... 
        // 2 ^ h - 1
        // 2의 7승으로 127까지 나타내니까 
        
        str = "0".repeat(len - str.length()) + str;
        
        return check(str) ? 1 : 0;
    }
    
    public boolean check(String str) {
        if (str.length() == 1) return true; 
        int mid = str.length() / 2;
        
        String left = str.substring(0, mid);
        String right = str.substring(mid + 1); 
        char root = str.charAt(mid);
        
        if (root == '0') {
           	if (left.contains("1") || right.contains("1")) {
                return false; 
            }
        }
        
        return check(left) && check(right);
    }
    
    public void print(Object o) {
        System.out.println(o);
    }
}