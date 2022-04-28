package Leetcode.AppleMock;

public class OnsiteQ1 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        while(slowPtr != null && fastPtr != null){
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next != null ? fastPtr.next.next : null;

            if(slowPtr != null && slowPtr == fastPtr) return true;
        }

        return false;
    }


    public static void main(String[] args){

    }

}
