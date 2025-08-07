class Solution {

    public int solution(int n) {
        int power = 0;

        while (n > 0) {
            // 짝수면 순간이동
            if (n % 2 == 0) n /= 2;
            // 홀수면 점프
            else {
                n--;
                power++;
            }
        }

        return power;
    }

}