package ctci.linkedlist;

public class ListNode{
    ListNode prev;
    ListNode next;
    int value;

    public ListNode() {}
    public ListNode(int value){
        this.value = value;
    }

    @Override
    public String toString(){
        return "ListNode value: " + this.value;
    }
}