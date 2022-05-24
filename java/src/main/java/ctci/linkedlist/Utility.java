package ctci.linkedlist;

public class Utility {
    static void printList(ListNode list){
        while(list != null) {
            System.out.print(list.value + " ");
            list = list.next;
        }
        System.out.println();
    }

    static ListNode createLinkedList(int[] data){
        ListNode head = new ListNode(-1);
        ListNode ptr = head;
        for(int i=0; i<data.length; ++i) {
            ListNode nn = new ListNode(data[i]);
            ptr.next = nn;
            nn.prev = ptr;

            ptr = ptr.next;
        }

        return head.next;
    }
}
