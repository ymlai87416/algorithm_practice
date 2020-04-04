package Leetcode;

import java.util.HashMap;

public class CopyListWithRandomPointer {
    public static void main(String[] args){
        Node root = new Node(1, null, null);
        root.next = new Node(2, null, null);
        root.random = root.next;
        root.next.random = root.next;

        Solution s = new Solution();
        System.out.println(s.copyRandomList(root));
    }


    static
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    };

    static
    class Solution {
        public Node copyRandomList(Node head) {
            HashMap<Node, Node> map = new HashMap<Node, Node>();

            Node result = new Node(-1, null, null);
            Node curNode = result;
            Node headT = head;
            while(headT != null){
                Node clone = new Node(headT.val, null, headT.random);
                map.put(headT, clone);

                curNode.next = clone;
                curNode = curNode.next;

                headT = headT.next;
            }

            curNode = result.next;
            while(curNode != null){
                if(curNode.random != null)
                    curNode.random = map.get(curNode.random);
                curNode = curNode.next;
            }

            return result.next;
        }
    }
}
