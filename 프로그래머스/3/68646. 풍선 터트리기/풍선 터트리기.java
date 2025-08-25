class Solution {
    public int solution(int[] a) {
        int n = a.length;
        int[] prefixMin = new int[n];
        int[] suffixMin = new int[n];
        
        prefixMin[0] = Integer.MAX_VALUE;
        for (int i=1; i<n; i++) {
           	prefixMin[i] = Math.min(prefixMin[i-1], a[i-1]);
        }
        
        suffixMin[n-1] = Integer.MAX_VALUE;
        for (int i=n-2; i>=0; i--) {
            suffixMin[i] = Math.min(suffixMin[i+1], a[i+1]);
        }
       
        int count = 0;
        for (int i=0; i<n; i++) {
            boolean leftMin = prefixMin[i] < a[i];
            boolean rightMin = suffixMin[i] < a[i];
            
            if (leftMin && rightMin) {
                continue;
            }
            count++;
        }
		// System.out.println(prefixMin[2] + " " + suffixMin[2]);
        
        return count;
    }
}