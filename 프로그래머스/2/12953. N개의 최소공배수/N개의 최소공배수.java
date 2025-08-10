class Solution {
    public int gcd(int a, int b) {
  		while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }      
        return a;
    }
  
    public int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
    
    public int lcmMulti(int[] arr) {
        int res = arr[0];
        for (int i=1; i<arr.length; i++) {
            res = lcm(res, arr[i]);
            if (res == 1) break;
        }
        return res;
    }
    
    public int solution(int[] arr) {
        int res = lcmMulti(arr);
        return res;
    }
}