package Leetcode;

/*
number: 141
url: https://leetcode.com/problems/linked-list-cycle-ii/
level: medium
solution: use floyd's cycle detection algorithm
 */
public class LinkedListCycleII {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode( 2);
        head.next.next = new ListNode( 0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;

        Solution sol = new Solution();
        System.out.println(sol.detectCycle(head).val);

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
        public ListNode detectCycle(ListNode head) {
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
                ptr1 = head;

                while(ptr1 != ptr2){
                    ptr1 = ptr1.next; ptr2 = ptr2.next;
                }

                return ptr1;
            }
            else
                return null ;
        }
    }
}
