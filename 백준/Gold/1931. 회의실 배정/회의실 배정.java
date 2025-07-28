import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Pair> list = new ArrayList<>();
        for (int i=0; i<N; i++) {
            String[] parts = br.readLine().split(" ");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            list.add(new Pair(x, y));
        }

        // y를 기준으로 내림차순
        Collections.sort(list, (a, b) -> {
            if (a.y == b.y) return a.x - b.x;
            return a.y - b.y;
        });

        List<Pair> result = new ArrayList<>();
        result.add(list.get(0));
        int curr = list.get(0).y;
        for (int i=1; i<list.size(); i++) {
            if (curr <= list.get(i).x) {
                result.add(list.get(i));
                curr = list.get(i).y;
            }
        }

//        for(Pair p: result) {
//            System.out.println(p.x + " " + p.y);
//        }

        System.out.println(result.size());
    }
}
