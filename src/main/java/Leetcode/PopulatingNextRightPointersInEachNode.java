package Leetcode;

public class PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {
        Node root = new Node(1, null, null, null);
        root.left = new Node(2, null, null, null);
        root.right = new Node(3, null, null, null);
        root.left.left = new Node(4, null, null, null);
        root.left.right = new Node(5, null, null, null);
        root.right.left = new Node(6, null, null, null);
        root.right.right = new Node(7, null, null, null);
        Solution sol = new Solution();
        Node r = sol.connect(root);
        System.out.println(r);
    }

    static
    public class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    static
    class Solution {
        public Node connect(Node root) {
            //prev left
            //curr left
            if(root == null) return root;
            if(root == null && root.left == root.right && root.left == null) return root;

            Node currLeft = root;

            while(currLeft != null){
                currLeft = connectHelper(currLeft);
            }

            return root;
        }

        public Node connectHelper(Node prevLeft){
            Node currLeft = null, currLeftT = null;
            while(prevLeft != null){
                if(currLeft == null) {
                    if (prevLeft.left != null) {
                        currLeft = currLeftT = prevLeft.left;
                        if(prevLeft.right != null){
                            currLeftT.next = prevLeft.right;
                            currLeftT = currLeftT.next;
                        }
                    }
                    else if (prevLeft.right != null) {
                        currLeft = currLeftT = prevLeft.left;
                    }
                }
                else{
                    if(prevLeft.left != null){
                        currLeftT.next = prevLeft.left;
                        currLeftT = currLeftT.next;
                    }
                    if(prevLeft.right != null){
                        currLeftT.next = prevLeft.right;
                        currLeftT = currLeftT.next;
                    }
                }

                prevLeft = prevLeft.next;
            }

            return currLeft;
        }
    }
}
