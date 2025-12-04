class Solution {
    public int solution(int[] a) {
        int n = a.length; 
        int[] prefixMin = new int[n];
        int[] postfixMin = new int[n];
        
        prefixMin[0] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            prefixMin[i] = Math.min(prefixMin[i - 1], a[i - 1]); 
        }
        
        postfixMin[n - 1] = Integer.MAX_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            postfixMin[i] = Math.min(postfixMin[i + 1], a[i + 1]); 
        }
        
        int result = 0; 
        for (int i = 0; i < n; i++) {
            if (a[i] > prefixMin[i] && a[i] > postfixMin[i]) continue;
            result++;
        }
        /*
        for (int num: prefixMin) {
            System.out.print(num + " "); 
        }
        System.out.println(); 
        for (int num: postfixMin) {
            System.out.print(num + " "); 
        }
        */ 
        return result;
    }
}