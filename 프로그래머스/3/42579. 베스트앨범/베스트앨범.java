import java.util.*;

class Solution {
    class Song implements Comparable<Song> {
        int index; 
        int freq; 
        
        public Song (int index, int freq) {
            this.index = index; 
            this.freq = freq; 
        }
        
        @Override
        public int compareTo(Song other) {
            return other.freq - this.freq;
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreFreqMap = new HashMap<>();
        Map<String, PriorityQueue<Song>> playFreqMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i]; 
            int playCnt = plays[i]; 
            
            genreFreqMap.put(genre, genreFreqMap.getOrDefault(genre, 0) + playCnt);
            playFreqMap.putIfAbsent(genre, new PriorityQueue<>());
            playFreqMap.get(genre).add(new Song(i, playCnt));
        }
        
        List<String> orders = new ArrayList<>(genreFreqMap.keySet());
        Collections.sort(orders, (a, b) -> genreFreqMap.get(b) - genreFreqMap.get(a));

        List<Integer> resultList = new ArrayList<>();
        
        // 순서에 맞게 모든 노래에 대해 
        for (String genre: orders) {
            PriorityQueue<Song> pq = playFreqMap.get(genre);
            Song play1 = pq.poll();
            resultList.add(play1.index);
            
            if (!pq.isEmpty()) {
           		Song play2 = pq.poll();
            	resultList.add(play2.index);
            }
        }        
        
        int n = resultList.size(); 
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = resultList.get(i);
        }
        return answer;
    }
}