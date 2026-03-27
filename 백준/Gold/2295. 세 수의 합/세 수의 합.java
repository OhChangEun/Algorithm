import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        // d (결과값)를 가장 큰 수부터 확인
        for (int k = n - 1; k >= 0; k--) {
            // z를 선택 (k와 같아도 됨)
            for (int z = n - 1; z >= 0; z--) {
                int target = arr[k] - arr[z];

                // x + y = target인 x, y 찾기 (중복 허용)
                int left = 0;
                int right = n - 1; // 범위 제한을 풀어서 모든 조합 확인
                
                while (left <= right) { // left == right 면 같은 수를 두 번 더함
                    int sum = arr[left] + arr[right];
                    if (sum == target) {
                        System.out.println(arr[k]);
                        return;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
    }
}