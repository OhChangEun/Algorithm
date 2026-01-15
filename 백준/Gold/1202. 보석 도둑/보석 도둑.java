import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k;
    static Gem[] gems;
    static int[] bags;

    static class Gem {
        int weight;
        int value;

        public Gem(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");
        n = Integer.parseInt(parts[0]);
        k = Integer.parseInt(parts[1]);

        gems = new Gem[n];

        for (int i = 0; i < n; i++) {
            parts = br.readLine().split(" ");
            int weight = Integer.parseInt(parts[0]);
            int value = Integer.parseInt(parts[1]);

            gems[i] = new Gem(weight, value);
        }

        bags = new int[k];
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(gems, (a, b) -> {
            if (a.weight != b.weight)
                return a.weight - b.weight;
            return b.value - a.value;
        });

        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long sum = 0;
        int right = 0;
        for (int left = 0; left < k; left++) {
            int bag = bags[left];

            while (right < n && gems[right].weight <= bag) {
                pq.offer(gems[right].value);
                right++;
            }

            if (!pq.isEmpty()) {
                sum += pq.poll();
            }
        }

        System.out.println(sum);
    }
}