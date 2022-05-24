package ctci.linkedlist;

public class ReturnKthLast {

    public ListNode returnKthLast(ListNode head, int k){

        ListNode p1 = head;
        ListNode p2 = head;

        for(int i=0; i<k; ++i){
            p2 = p2.next;
        }

        while(p2!=null){
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    public static void main(String[] args) {
        ReturnKthLast test = new ReturnKthLast();
        ListNode list1 = Utility.createLinkedList(new int[] {1,2,3,4,5,6});
        System.out.println(test.returnKthLast(list1, 3));
    }

}
