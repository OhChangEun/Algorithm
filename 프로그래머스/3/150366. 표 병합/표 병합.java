import java.util.*;

class Solution {
    final int NUM = 50; 
    final int SIZE = NUM * NUM;
    int[] parent;
    String[] values;
    
    public String[] solution(String[] commands) {
        parent = new int[SIZE];
        values = new String[SIZE];  
        
        for (int i = 0; i < SIZE; i++) {
            parent[i] = i;
        }
        
        List<String> result = new ArrayList<>();
        
        for (String cmd: commands) {
            String[] parts = cmd.split(" ");
            String op = parts[0];
            
            if (op.equals("UPDATE")) {
                if (parts.length == 4) { // UPDATE r c value
                    int r = Integer.parseInt(parts[1]);
                    int c = Integer.parseInt(parts[2]);
                    String value = parts[3];
                    
                    int idx = getIdx(r, c);
                    int root = find(idx);
                    values[root] = value; 
                    
                } else { // UPDATE value1 value2
                    String from = parts[1];
                    String to = parts[2]; 
                    
                    for (int i = 0; i < SIZE; i++) {
                        if (values[i] != null && values[i].equals(from)) {
                            values[i] = to;
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
                
                if (values[root1] == null && values[root2] != null) {
                    parent[root1] = root2; 
                } else {
                    parent[root2] = root1;
                }
            
            } else if (op.equals("UNMERGE")) { // UNMERGE r c
                int r = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);
                
                int idx = getIdx(r, c); 
                int root = find(idx);
                String saved = values[root];
                
                List<Integer> merged = new ArrayList<>();
                for (int i = 0; i < SIZE; i++) {
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
        }
       
        return result.toArray(new String[0]);
    }
    
    private int getIdx(int row, int col) {
        int idx = (row - 1) * NUM + (col - 1);
        return idx; 
    }
    
    private int find(int x) {
        if (parent[x] == x) {
            return x;
        } 
        return parent[x] = find(parent[x]);
    }
}