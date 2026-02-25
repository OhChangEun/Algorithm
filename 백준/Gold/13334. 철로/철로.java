import java.io.*;
import java.util.*;

public class Main {
    static int n;

    static class State {
        int from, to;
        public State(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        List<State> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);


            int from = Math.min(a, b);
            int to = Math.max(a, b);

            list.add(new State(from, to));
        }

        Collections.sort(list, (a, b) -> a.to - b.to);

        int maxCount = 0;
        int dist = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (State s : list) {
            int currEnd = s.to;
            int currRange = currEnd - dist;

            while (!pq.isEmpty() && pq.peek() < currRange) {
                pq.poll();
            }

            if (s.to - s.from <= dist) {
                pq.offer(s.from);
            }

            maxCount = Math.max(maxCount, pq.size());
        }

        System.out.println(maxCount);
    }
}
