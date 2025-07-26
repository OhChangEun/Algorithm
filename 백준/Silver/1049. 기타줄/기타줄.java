import java.util.*;
import java.io.*;

public class Main {

    public static int minVal(int[] arr) {
        int min = arr[0];

        for (int i=1; i<arr.length; i++) {
            if (min > arr[i])
                min = arr[i];
        }
        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr1 = new int[M];
        int[] arr2 = new int[M];

        for (int i=0; i<M; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            arr1[i] = Integer.parseInt(st1.nextToken());
            arr2[i] = Integer.parseInt(st1.nextToken());

            // 이런 말도 안되는 상황이 일어난다면
            // 묶음보다 개별이 더 싼 상황
//            if (arr1[i] > arr2[i] * 6)
//                arr1[i] = 1001;
        }

        int min1 = minVal(arr1);
        int min2 = minVal(arr2);

        int minVal1 = (N/6)*min1 + (N%6)*min2; // 6개 묶음과 낱개 묶음의 최소 조합
        int minVal2 = (N/6+1)*min1; // 6개 묶음만의 조합
        int minVal3 = N*min2; // 낱개 묶음의 최소 조합

//        System.out.println("minVal1: " + minVal1);
//        System.out.println("minVal2: " + minVal2);
        int res = Math.min(minVal1, minVal2);
        res = Math.min(res, minVal3);
        System.out.println(res);

    }
}