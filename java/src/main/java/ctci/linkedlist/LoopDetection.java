package ctci.linkedlist;

public class LoopDetection {

    public ListNode loopDetection(ListNode head){

        ListNode hare = head;
        ListNode tortoise = head;

        do{

            hare = hare.next;
            hare = hare == null ? null : hare.next;
            tortoise = tortoise.next;

            if(hare == null)
                return null;
        }while(hare != tortoise);

        //now we know that they meet.
        hare = head;

        while(hare != tortoise){
            hare = hare.next;
            tortoise = tortoise.next;
        }

        return hare;
    }


    public static void main(String[] args) {
        ListNode list = createCycleList();
        LoopDetection test = new LoopDetection();

        System.out.println(test.loopDetection(list));
    }

    private static ListNode createCycleList(){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);

        a.next = b; b.next = c; c.next = d; d.next = e;
        e.next = c;

        return a;
    }
}
