import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int SIZE = 9;

        List<Integer> list = new ArrayList<>();
        for (int i=0; i<SIZE; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        int max = Collections.max(list);
        int index = list.indexOf(max) + 1;

        System.out.println(max);
        System.out.println(index);
    }
}