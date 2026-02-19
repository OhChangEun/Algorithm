import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int[] arr;
        int cost;

        public Node(int[] arr, int cost) {
            this.arr = arr;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }

    static class SwapOp {
        int left;
        int right;
        int cost;

        public SwapOp(int left, int right, int cost) {
            this.left = left;
            this.right = right;
            this.cost = cost;
        }
    }

    private static String getKey(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num: arr) {
            sb.append(num).append(",");
        }

        return sb.toString();
    }

    static List<SwapOp> opList;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        String[] parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }

        opList = new ArrayList<>();
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            parts = br.readLine().split(" ");
            int left = Integer.parseInt(parts[0]) - 1; // 0-based
            int right = Integer.parseInt(parts[1]) - 1; // 0-based
            int cost = Integer.parseInt(parts[2]);

            opList.add(new SwapOp(left, right, cost));
        }

        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        String endKey = getKey(sortedArr);
        int result = dijkstra(arr, endKey);

        System.out.println(result);
    }

    private static int dijkstra(int[] arr, String endKey) {
        String start = getKey(arr);
        Map<String, Integer> dist = new HashMap<>();
        dist.put(start, 0);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(arr, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            String currKey = getKey(curr.arr);

            // 기존 비용보다 비싸면 pass
            if (curr.cost > dist.get(currKey)) continue;
            if (currKey.equals(endKey)) {
                return curr.cost;
            }

            for (SwapOp op: opList) {
                int[] nextArr = curr.arr.clone();
                swap(nextArr, op.left, op.right);

                String nextKey = getKey(nextArr);
                int nextCost = dist.get(currKey) + op.cost;
                if (nextCost < dist.getOrDefault(nextKey, Integer.MAX_VALUE)) {
                    dist.put(nextKey, nextCost);
                    pq.offer(new Node(nextArr, nextCost));
                }
            }
        }

        return -1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}