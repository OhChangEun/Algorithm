import java.util.*;

class Solution {
    List<Integer> preorderList;
    List<Integer> postorderList;
    
    class Node implements Comparable<Node>{
        int index;
        int y;
        int x; 
        Node left, right; 
        
        public Node(int index, int y, int x) {
            this.index = index;
            this.y = y;
            this.x = x;
            this.left = null;
            this.right = null;
        }
        
        @Override
        public int compareTo(Node other) {
            return other.y - this.y;
        }
    }
    public int[][] solution(int[][] nodeinfo) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        int n = nodeinfo.length; 
        for (int i = 0 ; i < n; i++) {
            int y = nodeinfo[i][1];
            int x = nodeinfo[i][0]; 
            
            pq.offer(new Node(i + 1, y, x));
        }
       
        Node root = pq.poll();
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            insertNode(root, node);
        }
        
        preorderList = new ArrayList<>();
        postorderList = new ArrayList<>();
       
        preorder(root);
        postorder(root);
       
        int[][] answer = new int[2][n];
        for (int i = 0; i < n; i++) {
            answer[0][i] = preorderList.get(i);
            answer[1][i] = postorderList.get(i);
        }
        return answer;
    }
    
    private void preorder(Node node) {
        if (node == null) 
            return; 
        
        preorderList.add(node.index);
        preorder(node.left);
        preorder(node.right);
    }
    
    private void postorder(Node node) {
        if (node == null) 
            return; 
        
        postorder(node.left);
        postorder(node.right);
        postorderList.add(node.index);
    }
    
    private void insertNode(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) {
                parent.left = child; 
            } else {
                insertNode(parent.left, child);
            }
        } else {
           if (parent.right == null) {
               parent.right = child; 
           } else {
               insertNode(parent.right, child);
           }
        }
    }
    
    private void print(int[][] arr) {
        for (int[] row: arr) {
            for (int num: row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}