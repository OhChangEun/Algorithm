class Solution {
   	public int getOneCount(String input) {
    	int count = 0;	
        for (char ch: input.toCharArray()) {
            if (ch == '1')
                count++;
        }    
        return count;
    } 
    public int solution(int n) {
        
        String binary1 = Integer.toBinaryString(n);
        int num1 = getOneCount(binary1);

        int next = ++n;
        while (true) {
        	String binary2 = Integer.toBinaryString(next);
       		int num2 = getOneCount(binary2);     
           
            if (num1 == num2) 
                return next;
                
            next++;
        }
        // System.out.println(binary1); 
    }
}