import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        Map<String, Integer> sums = new HashMap<>();
        Map<String, List<int[]>> list = new HashMap<>();
        
        for (int i=0; i<genres.length; i++) {
            sums.put(genres[i], sums.getOrDefault(genres[i], 0) + plays[i]);
           
            // 들어갈 공간 만들어주고,
            if (!list.containsKey(genres[i])) {
                list.put(genres[i], new ArrayList<>());
            }
            list.get(genres[i]).add(new int[] {i, plays[i]});
        }
        
        List<String> genreOrder = new ArrayList<>(sums.keySet());
        genreOrder.sort((a, b) -> sums.get(b) - sums.get(a));
        
        List<Integer> result = new ArrayList<>();
        for (String genre: genreOrder) {
            List<int[]> songs = list.get(genre);
            songs.sort((a, b) -> {
                if (a[1] == b[1]) return a[0] - b[0];
             	return b[1] - a[1]; 
            });
            
            for (int i=0; i<Math.min(2, songs.size()); i++) {
                result.add(songs.get(i)[0]);
            }
        }
        
        int[] res = new int[result.size()];
        for (int i=0; i<result.size(); i++) {
            res[i] = result.get(i);
        }
        
        return res;
    }
}