package ctci.linkedlist;

import jdk.jshell.execution.Util;

public class SumLists {
    public ListNode sumLists(ListNode num1, ListNode num2){

        ListNode result = new ListNode();
        ListNode ptr1 = num1;
        ListNode ptr2 = num2;
        ListNode ptr3 = result;

        int carry = 0;

        while(ptr1 != null || ptr2 != null || carry > 0){

            int a = ptr1 == null ? 0: ptr1.value;
            int b = ptr2 == null ? 0: ptr2.value;
            int v = a+b + carry;
            int r = v %10;
            carry = v >= 10 ? 1: 0;

            ptr1 = ptr1== null ? null : ptr1.next;
            ptr2 = ptr2== null ? null : ptr2.next;

            ptr3.next = new ListNode(r);
            ptr3 = ptr3.next;
        }

        return result.next;
    }


    public static void main(String[] args) {
        ListNode list1 = Utility.createLinkedList(new int[]{7,1,6});
        ListNode list2 = Utility.createLinkedList(new int[]{5,9,2});
        SumLists test = new SumLists();

        ListNode result = test.sumLists(list1, list2);

        Utility.printList(result);
    }
}
