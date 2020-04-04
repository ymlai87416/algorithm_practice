package Leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
number: 23
url: https://leetcode.com/problems/merge-k-sorted-lists/
level: hard
solution: this solution use priority queue to add the head of the k sorted list
 */

public class MergeKSortedList {
    public class ListNode {
        int val;
        ListNode next;
       ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        String i = "()[]{}";
        ValidParentheses.Solution s = new ValidParentheses.Solution();
        System.out.println(s.isValid(i));
    }


    static
    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>(){

                @Override
                public int compare(ListNode o1, ListNode o2) {
                    if (o1.val < o2.val) return -1; else if (o1.val > o2.val) return 1; else return 0;
                }
            });

            for(int i=0; i<lists.length; ++i){
                if(lists[i] != null)
                    pq.add(lists[i]);
            }

            ListNode root = null;
            ListNode curNode = null;
            while (!pq.isEmpty()) {
                ListNode u = pq.poll();
                if(curNode ==null){
                    root = u;
                    curNode = u;
                }
                else{
                    curNode.next = u;
                    curNode = u;
                }

                if(u.next != null) pq.offer(u.next);
            }

            return root;
        }
    }
}
