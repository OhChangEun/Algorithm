import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");
        long a = Long.parseLong(parts[0]);
        long b = Long.parseLong(parts[1]);

        System.out.println(countOnes(b) - countOnes(a - 1));
    }

    private static long countOnes(long x) {
        long cnt = 0;
        long power = 1;

        while (power <= x) {
            long cycle = power * 2;
            long div = (x + 1) / cycle;
            long remain = (x + 1) % cycle;

            cnt += div * power;

            if (remain > power) {
                cnt += remain % power;
            }

            power *= 2;
        }

        return cnt;
    }
}