package Leetcode;

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
            return null;
        }
    }
}


class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}