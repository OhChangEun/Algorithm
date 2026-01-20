import java.util.*;

class Solution {
    List<Integer> inorderList;
    List<Integer> preorderList;
    
    class Node {
        int idx; 
        int y, x;
        Node left, right; 
        
        public Node(int idx, int y, int x) {
            this.idx = idx;
            this.y = y;
            this.x = x;
            this.left = null;
            this.right = null;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.y != b.y) return b.y - a.y;
            else return a.x - b.x;
        });
        
        int n = nodeinfo.length;
        for (int i = 0; i < n; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            
            pq.add(new Node(i + 1, y, x));
        }
        
        Node root = pq.poll();
        while (!pq.isEmpty()) {
            Node node = pq.poll(); 
            insertNode(root, node);
        }
        
        inorderList = new ArrayList<>();
        preorderList = new ArrayList<>();
       
        inorder(root);
       	preorder(root); 
       
        int arrSize = inorderList.size();
        int[][] answer = new int[2][arrSize];
        for (int i = 0; i < arrSize; i++) {
            answer[0][i] = inorderList.get(i);
            answer[1][i] = preorderList.get(i);
        }
        return answer;
    }
    
    private void inorder(Node curr) {
        if (curr == null) return; 
        
        inorderList.add(curr.idx);
        inorder(curr.left);
        inorder(curr.right);
    }
    
    private void preorder(Node curr) {
        if (curr == null) return; 
        
        preorder(curr.left);
        preorder(curr.right);
        preorderList.add(curr.idx);
    }
    
    private void insertNode(Node root, Node node) {
        // x 좌표가 작으면 노드의 왼쪽에 
        if (node.x < root.x) {
            if (root.left == null) {
                root.left = node;
            } else {
                insertNode(root.left, node);
            }
        } else { // x 좌표가 크면 노드의 오른쪽에 
            if (root.right == null) {
                root.right = node; 
            } else {
                insertNode(root.right, node);
            }
        }
    }
}