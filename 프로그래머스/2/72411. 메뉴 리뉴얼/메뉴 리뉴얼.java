import java.util.*;

class Solution {
    Map<String, Integer> menuMap; 

    public String[] solution(String[] orders, int[] course) {
        
        List<String> resultList = new ArrayList<>();
        // course의 개수가 2인 경우에 대해(모든 course로 변경하기)
        // orders로 만들 수 있는 모든 코스 경우의 수 
        // ex: AB, AC, AF, AG.. 
        // 모든 course를 돌았을 때, 2개 이상에 대해 나왔다면 value를 활용하여 최대값 찾기 
        // value가 최대값인 모든 key 값을 result에 추가 

        for (int courseSize: course) {
            menuMap = new HashMap<>();
            for (String order: orders) {
                char[] menu = order.toCharArray();
                Arrays.sort(menu);
                // 만들 수 있는 모든 메뉴의 경우 map에 담기 
                dfs(menu, courseSize, 0, "", 0);
            }

            // 가장 많이 등장한 최대 개수 
            int maxCnt = 0; 
            for (int cnt: menuMap.values()) {
                maxCnt = Math.max(maxCnt, cnt);
            }

            if (maxCnt >= 2) {
                for (Map.Entry<String, Integer> entry: menuMap.entrySet()) {
                    if (entry.getValue() == maxCnt) {
                        resultList.add(entry.getKey());
                    }
                }
            }
        }
        
        Collections.sort(resultList);
        
        return resultList.toArray(new String[0]);
    }
    
    private void dfs(char[] menu, int size, int depth, String curr, int start) {
        if (depth == size) {
            // System.out.println(curr);
            menuMap.put(curr, menuMap.getOrDefault(curr, 0) + 1);
            return; 
        }
        
        for (int i = start; i < menu.length; i++) {
            dfs(menu, size, depth + 1, curr + menu[i], i + 1);
        }
    }
}