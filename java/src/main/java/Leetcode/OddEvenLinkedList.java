package Leetcode;

/*
number: 328
url: https://leetcode.com/problems/odd-even-linked-list/
level: medium
solution: just keep track of the pointers
 */

public class OddEvenLinkedList {
    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        //nums = new int[0];
        Solution sol = new Solution();
        ListNode n = sol.oddEvenList(root);
        System.out.println(n);
    }

    static
    class Solution {
        public ListNode oddEvenList(ListNode head) {
            ListNode odd = new ListNode(0);
            ListNode even = new ListNode(0);

            ListNode oddS = odd, evenS = even;

            int i =1;
            for(ListNode ptr=head; ptr!=null; ptr=ptr.next){
                if(i %2 ==0) {
                    even.next = ptr;
                    even = even.next;
                }
                else{
                    odd.next = ptr;
                    odd = odd.next;
                }
                i++;
            }

            odd.next = evenS.next;
            even.next = null;

            return oddS.next;
        }
    }

    static
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
