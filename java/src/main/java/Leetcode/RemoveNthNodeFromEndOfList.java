package Leetcode;

/**
number: 19
problem: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
level: medium
solution: for a list delete n node from end and without prev, just maintain 2 pointers.

 #linkedList #twoPointers

 **/

public class RemoveNthNodeFromEndOfList {

    public static void main(String[] args){
        ListNode head = null;
        int n = 2;
        Solution a = new Solution();
        System.out.println(a.removeNthFromEnd(head, n));
    }


    static
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode a = head;
            ListNode b = head;
            for(int i=0; i<n+1; ++i) {
                if(b != null)
                    b = b.next;
                else return a.next;
            }
            while(b != null) {
                a = a.next;
                b = b.next;
            }
            a.next = a.next.next;

            return head;
        }
    }

    static
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}

