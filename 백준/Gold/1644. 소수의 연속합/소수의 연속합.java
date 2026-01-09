import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primeList = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }

        int primeCount = 0;
        int sum = 0;
        int left = 0;
        for (int right = 0; right < primeList.size(); right++) {
            sum += primeList.get(right);
            while (sum > n) {
                sum -= primeList.get(left);
                left++;
            }

            if (sum == n) {
                primeCount++;
            }
        }

        System.out.println(primeCount);
    }
}