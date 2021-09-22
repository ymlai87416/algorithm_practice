package Leetcode;

/**
problem: https://leetcode.com/problems/delete-node-in-a-linked-list/
level: easy
solution:

#linked_list

 */

public class DeleteNodeInALinkedList {
    public static void main(String[] args){
        ListNode head = new ListNode(4);
        head.next = new ListNode(5);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(9);

        Solution sol = new Solution();
        sol.deleteNode(head);
    }

    static
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    static
    class Solution {
        public void deleteNode(ListNode node) {
            if(node.next == null) return;
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }
}
