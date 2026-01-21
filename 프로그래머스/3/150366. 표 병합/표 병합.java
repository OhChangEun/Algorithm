import java.util.*;

class Solution {
    int[] parent;
    public String[] solution(String[] commands) {
        int size = 50 * 50; 
        
        String[] values = new String[size];         
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i; 
        }        
        
        List<String> result = new ArrayList<>();
        
        for (String cmd: commands) {
            String[] parts = cmd.split(" ");
            String op = parts[0];
            
            if (op.equals("UPDATE")) {
                // UPDATE r c value
                if (parts.length == 4) {
                    int r = Integer.parseInt(parts[1]);
                    int c = Integer.parseInt(parts[2]);
                    String value = parts[3];
                    
                    int idx = getIdx(r, c);
                    int root = find(idx);
                    values[root] = value;
                } else { // UPDATE value1 value2
                    String value1 = parts[1];
                    String value2 = parts[2];
                    
                    for (int i = 0; i < size; i++) {
                        if (values[i] != null && values[i].equals(value1)) {
                            values[i] = value2;
                        }
                    }
                }
            } else if (op.equals("MERGE")) { // MERGE r1 c1 r2 c2
                int r1 = Integer.parseInt(parts[1]);
                int c1 = Integer.parseInt(parts[2]);
                int r2 = Integer.parseInt(parts[3]);
                int c2 = Integer.parseInt(parts[4]);
                
                int idx1 = getIdx(r1, c1);
                int idx2 = getIdx(r2, c2);
                
                int root1 = find(idx1);
                int root2 = find(idx2); 
                
                if (root1 == root2) continue;
                
                // root2만 값을 가지는 경우: root2가 부모 
                if (values[root1] == null && values[root2] != null) {
                    parent[root1] = root2; 
                } else { // root1이 값이 있는 경우와 root1과 root2 둘다 값을 가지는 경우: root1이 부모 
                    parent[root2] = root1;
                }
            } else if (op.equals("UNMERGE")) { // UNMERGE r c 
                int r = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);
                
                int idx = getIdx(r, c); 
                int root = find(idx);
                String saved = values[root];
                
                List<Integer> merged = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    if (find(i) == root) {
                        merged.add(i);
                    }
                }
                
                for (int i: merged) {
                    parent[i] = i; 
                    values[i] = null;
                }
                
                values[idx] = saved;
            } else if (op.equals("PRINT")) { // PRINT r c
                int r = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);
                
                int idx = getIdx(r, c);
                int root = find(idx);
                result.add(values[root] == null ? "EMPTY" : values[root]);
            }
            //System.out.println(parts[0]);
        }
        
        return result.toArray(new String[0]);
    }
    
    private int getIdx(int r, int c) {
        int idx = (r - 1) * 50 + (c - 1);
        return idx; 
    }
    
    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        
        return parent[x] = find(parent[x]);
    }
}