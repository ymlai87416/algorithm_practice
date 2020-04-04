package Leetcode;

/*
number: 234
url: https://leetcode.com/problems/palindrome-linked-list/
level: easy
solution: reverse the list (recursion is simple) and now I just compare 2 linked list
 */

public class PalindromeLinkedList {
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        Solution s = new Solution();
        System.out.println(s.isPalindrome(head));
    }

    static
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    static
    class Solution {
        public boolean isPalindrome(ListNode head) {
            //ListNode reverse = reverseList(head);

            ListNode p1 = head, p2 = head;
            while(true){
                if(p2 != null && p2.next != null){
                    p2 = p2.next.next;
                    p1 = p1.next;
                }
                else break;
            }

            ListNode reverseLast = inPlaceReverseList(p1);
            while(head != null && reverseLast != null){
                if(head.val != reverseLast.val) return false;
                head = head.next;
                reverseLast= reverseLast.next;
            }

            return true;
        }

        //this algo create a new reverse list
        private ListNode reverseList(ListNode head){
            ListNode p1, p2, p3;

            if(head == null) return null;
            if(head.next == null) return new ListNode(head.val);

            p1 = null; p2 = new ListNode(head.val);
            p3 = head.next;

            while(p3 != null){

                ListNode temp = new ListNode(p3.val);
                temp.next = p2;

                p1 = p2;
                p2 = temp;

                p3 = p3.next;
            }

            return p2;
        }

        private ListNode inPlaceReverseList(ListNode head){
            ListNode p1, p2, p3;

            if(head == null) return null;
            if(head.next == null) return head;

            p1 = null; p2 = head;
            p3 = head.next;
            p2.next = null;

            while(p3 != null){
                p1 = p3.next;

                p3.next = p2;
                p2 = p3;
                p3 = p1;
            }

            return p2;
        }
    }

}
