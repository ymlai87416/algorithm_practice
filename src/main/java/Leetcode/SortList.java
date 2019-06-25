package Leetcode;

public class SortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        Solution sol = new Solution();
        ListNode result = sol.sortList(head);

        while(result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println();
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
    class Solution {
        public ListNode sortList(ListNode head) {
            return mergeSort(head);
        }


        //why that slow, it is 23ms and rank lowest 13
        public ListNode mergeSort(ListNode head){
            ListNode[] twoList = breakList(head);

            if(twoList == null)
                return head;

            ListNode newHead, curr;
            ListNode ptr1=twoList[0], ptr2=twoList[1];

            ptr1 = mergeSort(ptr1);
            ptr2 = mergeSort(ptr2);

            if(ptr1.val <= ptr2.val){
                newHead = ptr1;
                ptr1 = ptr1.next;
            }
            else{
                newHead = ptr2;
                ptr2 = ptr2.next;
            }

            curr = newHead;

            while(ptr1 != null || ptr2 != null){
                if(ptr1 != null && ptr2 != null && ptr1.val <= ptr2.val){
                   curr.next = ptr1;
                   ptr1 = ptr1.next;
                }
                else if(ptr1 != null && ptr2 != null && ptr1.val > ptr2.val){
                    curr.next = ptr2;
                    ptr2 = ptr2.next;
                }
                else if(ptr1 == null){
                    curr.next = ptr2;
                    ptr2 = ptr2.next;
                }
                else if(ptr2 == null){
                    curr.next = ptr1;
                    ptr1 = ptr1.next;
                }
                curr = curr.next;
            }

            curr.next = null;

            return newHead;
        }

        public ListNode[] breakList(ListNode head){
            ListNode ptr1 = head, ptr2 = head, ptr3 = null;

            while(true){
                if(ptr2 != null && ptr2.next != null) {
                    ptr2 = ptr2.next.next;
                    ptr3 = ptr1;
                    ptr1 = ptr1.next;
                }
                else break;
            }

            if(ptr2 != ptr1){
                ptr3.next = null;

                return new ListNode[]{head, ptr1};
            }
            else
                return null;
        }
    }
}
