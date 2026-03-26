import java.io.*;
import java.util.*;

public class Main {
    static int x, y;
    static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static String[] tags = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

    static final int MOD = 9901;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");

        x = Integer.parseInt(parts[0]);
        y = Integer.parseInt(parts[1]);

        int totalDay = 0;
        for (int i = 0; i < x; i++) {
            totalDay += days[i];
        }

        int day = totalDay + y;

        System.out.println(tags[day % 7]);

    }
}