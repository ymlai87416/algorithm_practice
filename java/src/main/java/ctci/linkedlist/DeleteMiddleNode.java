package ctci.linkedlist;

public class DeleteMiddleNode {


    public void deleteMiddleNodeX(ListNode head, ListNode removeNode){

        while(head.next != removeNode){

            head = head.next;
        }

        head.next = removeNode.next;
    }

    public boolean deleteMiddleNode(ListNode removeNode) {
        if(removeNode == null || removeNode.next == null)
            return false;
        ListNode ptr= removeNode;
        while(ptr != null){
            ListNode next = ptr.next;
            if(ptr.next != null) {
                ptr.value = ptr.next.value;
                ptr.next = ptr.next.next;
            }

            ptr=  next;
        }
        return true;
    }



    public static void main(String[] args) {
        DeleteMiddleNode test=  new DeleteMiddleNode();

        ListNode list1 = Utility.createLinkedList(new int[]{1,2,3,4,5,6});
        ListNode removeNode = list1.next.next.next;

        test.deleteMiddleNode(removeNode);

        Utility.printList(list1);
    }
}
