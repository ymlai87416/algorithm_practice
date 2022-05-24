package ctci.linkedlist;

public class Partition {

    public ListNode partition(ListNode head, int partition){

        ListNode smallerHead = new ListNode();
        ListNode smallerPtr = smallerHead;

        ListNode biggerHead = new ListNode();
        ListNode biggerPtr = biggerHead;

        ListNode ptr = head;
        while(ptr != null){
            ListNode next = ptr.next;
            if(ptr.value < partition){
                smallerPtr.next = ptr;
                ptr.next = null;
                smallerPtr = smallerPtr.next;
            }
            else{
                biggerPtr.next = ptr;
                ptr.next = null;
                biggerPtr = biggerPtr.next;
            }

            ptr = next;
        }

        if(smallerHead.next == null)
            return biggerHead.next;
        else{
            //we also need to keep track of the last element
            smallerPtr.next = biggerHead.next;
            return smallerHead.next;
        }
    }

    public static void main(String[] args) {
        ListNode list1 = Utility.createLinkedList(new int[]{3,5,8,5,10,2,1});

        Partition test=  new Partition();
        ListNode result = test.partition(list1, 5);

        Utility.printList(result);
    }
}
