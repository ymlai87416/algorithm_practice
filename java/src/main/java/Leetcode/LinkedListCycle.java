package Leetcode;

/*
number: 141
url: https://leetcode.com/problems/linked-list-cycle/
level: easy
solution: use floyd's cycle detection algorithm
 */

public class LinkedListCycle {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode( 2);
        head.next = new ListNode( 0);
        head.next = new ListNode(-4);
        Solution sol = new Solution();
        System.out.println(sol.hasCycle(head));

    }

    static
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    static
    public class Solution {
        public boolean hasCycle(ListNode head) {
            ListNode ptr1 = head;
            ListNode ptr2 = head;

            do{
                try {
                    ptr1 = ptr1.next;
                    ptr2 = ptr2.next.next;
                }
                catch(Exception ex) { ptr1 = null; ptr2 = null; break;}
            } while(ptr1 != ptr2);

            if(ptr1==ptr2 && ptr1 != null){
                return true;
            }
            else
                return false;
        }
    }
}
