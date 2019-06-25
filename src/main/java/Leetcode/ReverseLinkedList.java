package Leetcode;

import java.util.Stack;

public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        Solution s = new Solution();
        System.out.println(s.reverseList(head));
    }

    static
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static
    class Solution {
        public ListNode reverseList(ListNode head) {

            Stack<ListNode> stack = new Stack<ListNode>();
            while (head != null) {
                stack.push(head);
                head = head.next;
            }

            if (stack.isEmpty()) return null;
            ListNode result = stack.pop();
            ListNode curr = result;
            while (!stack.isEmpty()) {
                curr.next = stack.pop();
                curr = curr.next;
            }
            curr.next = null;

            return result;
        }
    }
}
