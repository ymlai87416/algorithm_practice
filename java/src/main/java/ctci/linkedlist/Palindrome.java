package ctci.linkedlist;
import java.util.*;

public class Palindrome {

    public boolean palindrome(ListNode head){

        ListNode p1 = head;
        ListNode p2 = head;

        Stack<ListNode> st = new Stack<>();

        while(p2!=null){
            st.push(p1);
            p1 = p1.next;
            p2 = p2.next;
            if(p2 == null) st.pop();
            p2 = p2 == null ? null : p2.next;
        }

        while(p1!=null){
            if(st.isEmpty()) return false;
            ListNode sl = st.pop();
            if(sl.value != p1.value) return false;
            p1 = p1.next;
        }

        return true;
    }

    public static void main(String[] args) {
        ListNode l1 = Utility.createLinkedList(new int[]{1,2,3,2,1});
        ListNode l2 = Utility.createLinkedList(new int[]{1,2,3,3,2,1});
        ListNode l3 = Utility.createLinkedList(new int[]{1});
        ListNode l4 = Utility.createLinkedList(new int[]{1,2});

        Palindrome p = new Palindrome();
        System.out.println(p.palindrome(l1));
        System.out.println(p.palindrome(l2));
        System.out.println(p.palindrome(l3));
        System.out.println(p.palindrome(l4));
    }
}
