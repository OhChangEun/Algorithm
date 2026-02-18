import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        String[] parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }

        int len = 0;
        int[] lis = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        for (int i = 0; i < n; i++) {

            int left = 0;
            int right = len - 1;
            int target = len;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (arr[lis[mid]] >= arr[i]) {
                    target = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            if (target > 0) {
                parent[i] = lis[target - 1];
            }
            lis[target] = i;

            if (len == target) {
                len++;
            }
        }

        List<Integer> result = new ArrayList<>();
        int curr = lis[len - 1];
        while (curr != -1) {
            result.add(arr[curr]);
            curr = parent[curr];
        }

        Collections.reverse(result);

        StringBuilder sb = new StringBuilder();
        sb.append(len).append("\n");
        for (int num: result) {
            sb.append(num).append(" ");
        }

        System.out.println(sb.toString());
    }
}