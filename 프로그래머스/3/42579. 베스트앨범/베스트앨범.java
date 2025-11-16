import java.util.*;

class Solution {
    class Song {
        int index;
        int value;
        public Song(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> sums = new HashMap<>();
        Map<String, List<Song>> songList = new HashMap<>();
        
        for (int i=0; i<genres.length; i++) {
            sums.put(genres[i], sums.getOrDefault(genres[i], 0) + plays[i]);
            
            if (!songList.containsKey(genres[i])) {
                songList.put(genres[i], new ArrayList<>());
            }
            
            songList.get(genres[i]).add(new Song(i, plays[i]));
        }
        // System.out.println(sums); 
        // System.out.println(songList); 
       
        List<String> genreOrder = new ArrayList<>(sums.keySet());
        genreOrder.sort((a, b) -> sums.get(b) - sums.get(a));
        // System.out.println(genreOrder); 
      
        List<Integer> result = new ArrayList<>();
        for (String genre: genreOrder) {
            List<Song> songs = songList.get(genre);
            songs.sort((a, b) -> {
                if (a.value == b.value) return a.index - b.index; 
                return b.value - a.value;
            });
            
            for (int i=0; i<Math.min(2, songs.size()); i++) {
                result.add(songs.get(i).index); 
            }
        }
      
        int n = result.size();
        int[] res = new int[n];
        for (int i=0; i<n; i++) {
            res[i] = result.get(i);
        }
        
        return res;
    }
}