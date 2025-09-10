class Solution {
    public long solution(int w, int h) {
        long gcdVal = gcd(w, h);
        return (long)w * h - (w + h - gcdVal);
    }
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b; 
            b = a % b;
            a = temp; 
        }
        return a;
    }
}