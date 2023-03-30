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

    public ListNode sumListsForward(ListNode num1, ListNode num2){

        //get the length of each list
        int n1Len = 0;
        int n2Len = 0;

        ListNode ptr1 = num1;
        ListNode ptr2 = num2;

        while(ptr1 != null){
            n1Len++;
            ptr1 = ptr1.next;
        }

        while(ptr2 != null){
            n2Len++;
            ptr2 = ptr2.next;
        }

        //now we pad the short one
        if(n1Len < n2Len) {
            ptr1 = num2;
            ptr2 = num1;
        }
        else{
            ptr1 = num1;
            ptr2 = num2;
        }

        if(n1Len != n2Len){
            ListNode pad0 = new ListNode(0);
            ListNode pad0R = pad0;

            for(int i=1; i< Math.abs(n1Len - n2Len); ++i){
                pad0.next = new ListNode(0);
                pad0 = pad0.next;
            }

            pad0.next = ptr2;
            ptr2 = pad0R;
        }

        return add(ptr1, ptr2);
    }

    private ListNode add(ListNode n1, ListNode n2){

        if(n1 != null){
            ListNode sub = add(n1.next, n2.next);
            ListNode r = new ListNode(n1.value+n2.value);
            if(sub != null && sub.value >= 10){
                sub.value -=10;
                r.value+=1;
            }
            r.next = sub;
            return r;
        }
        else
            return null;
    }



    public static void main(String[] args) {
        ListNode list1 = Utility.createLinkedList(new int[]{7,1,6});
        ListNode list2 = Utility.createLinkedList(new int[]{5,9,2});
        SumLists test = new SumLists();

        ListNode result = test.sumLists(list1, list2);

        Utility.printList(result);

         list2 = Utility.createLinkedList(new int[]{6,1,7});
         list1 = Utility.createLinkedList(new int[]{9,5});

        result = test.sumListsForward(list1, list2);
        Utility.printList(result);
    }
}
