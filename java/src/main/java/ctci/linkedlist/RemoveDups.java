package ctci.linkedlist;

import java.util.*;

public class RemoveDups {

    public void removeDups(ListNode head){

        HashSet<Integer> member = new HashSet<>();

        while(head != null){

            if(member.contains(head.value)){
                ListNode next = head.next;

                head.prev.next = head.next;
                if(head.next!= null)
                    head.next.prev = head.prev;

                head = next;
            }
	        else{
                member.add(head.value);
                head = head.next;
            }
        }
    }

    public void removeDups2(ListNode head){

        ListNode i, j;
        i = head;

        while(i != null){
            //go from the beginning
            j = head;
            boolean duplicateFound = false;
            while(j != i ){
                if (j.value == i.value){
                    duplicateFound = true;
                    break;
                }
                j = j.next;
            }

            if(duplicateFound){
                i.prev.next = i.next;
                if(i.next != null)
                    i.next.prev = i.prev;
            }

            i = i.next;
        }

    }



    public static void main(String[] args) {
        RemoveDups test = new RemoveDups();

        ListNode testList1 = Utility.createLinkedList(new int[]{1,2,3,4,5});
        ListNode testList2 = Utility.createLinkedList(new int[]{1,2,3,4,4});
        test.removeDups2(testList1);
        test.removeDups2(testList2);

        Utility.printList(testList1);
        Utility.printList(testList2);
    }




}

