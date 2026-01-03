import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        long[] res = fib(n);
        System.out.println(res[0]);
    }

    private static long[] fib(long n) {
        if (n == 0)
            return new long[] {0, 1};

        long[] half = fib(n / 2);
        long a = half[0]; // K(n)
        long b = half[1]; // K(n+1)

        long c = ((a % MOD) * ((2 * b % MOD) - a + MOD) % MOD) % MOD; // K(2k)
        long d = ((a * a % MOD) + (b * b % MOD)) % MOD; // K(2k + 1)

        if (n % 2 == 0) {
            return new long[] {c, d};
        } else {
            return new long[] {d, (c + d) % MOD};
        }
    }
}