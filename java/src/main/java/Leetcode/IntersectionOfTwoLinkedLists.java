package Leetcode;

/**
number: 160
problem: https://leetcode.com/problems/intersection-of-two-linked-lists/
level: easy
solution: find out the length of linked list A and B, then start at a position in each array such that the length of
    remaining node is the same. the pointer value will be the same at some point.

#linked_list #two_pointers

 */

public class IntersectionOfTwoLinkedLists {

    static
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ptr1 = headA;
        ListNode ptr2 = headB;
        int lenA, lenB;
        lenA = 0; lenB = 0;

        if(ptr1 == null || ptr2 == null) return null;

        while(ptr1.next != null){
            lenA += 1;
            ptr1 = ptr1.next;
        }
        while(ptr2.next != null){
            lenB += 1;
            ptr2 = ptr2.next;
        }

        if(ptr1.val == ptr2.val){
            int diff = Math.abs(lenA-lenB);
            //System.out.format("%d %d %d\n", lenA, lenB, diff);
            if(lenA < lenB){
                ptr1 = headA;
                ptr2 = headB;
                for(int i=0; i<diff; ++i)
                    ptr2 = ptr2.next;

                while(ptr1 != ptr2){
                    ptr1 = ptr1.next;
                    ptr2 = ptr2.next;
                }

                return ptr1;
            }
            else{
                ptr1 = headA; ptr2 = headB;
                for(int i=0; i<diff; ++i)
                    ptr1 = ptr1.next;

                while(ptr1.val != ptr2.val){
                    ptr1 = ptr1.next;
                    ptr2 = ptr2.next;
                }

                return ptr1;
            }
        }
        else
            return null;
    }
}
