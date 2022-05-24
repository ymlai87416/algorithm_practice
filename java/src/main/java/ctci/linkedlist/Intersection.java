package ctci.linkedlist;

public class Intersection {
    public boolean intersection(ListNode a, ListNode b){
        int aLen = 0; int bLen = 0;
        ListNode ap = a;
        ListNode bp = b;

        while(ap.next != null){
            aLen ++;
            ap = ap.next;
        }
        while(bp.next != null){
            bLen++;
            bp = bp.next;
        }

        //now we know
        ListNode sp = aLen > bLen ? b: a;
        ListNode lp = aLen > bLen ? a: b;

        //now advance the long pointer to diff
        int diff = Math.abs(bLen - aLen);
        for(int i=0; i<diff; ++i)
            lp = lp.next;


        while(lp.next != null){
            if(lp == sp) return true;

            lp = lp.next;
            sp = sp.next;
        }

        return false;
    }


    public static void main(String[] args) {
        Intersection test = new Intersection();

        ListNode list1 = Utility.createLinkedList(new int[]{1,2,3,4,5});
        ListNode list2 = Utility.createLinkedList(new int[]{7,8,9});
        System.out.println(test.intersection(list1, list2));

        list1.next.next.next.next.next = list2;
        System.out.println(test.intersection(list1, list2));
    }
}
