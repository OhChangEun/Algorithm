import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int len = 0;
        int[] lis = new int[n];
        String[] parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int curr = Integer.parseInt(parts[i]);

            if (len == 0 || lis[len - 1] < curr) {
                lis[len] = curr;
                len++;
            } else {
                int left = 0;
                int right = len - 1;

                while (left <= right) {
                    int mid = (left + right) / 2;

                    if (lis[mid] < curr) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                lis[left] = curr;
            }
        }

        System.out.println(len);
    }
}