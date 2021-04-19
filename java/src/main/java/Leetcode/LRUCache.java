package Leetcode;

import java.util.HashMap;

/*
number: 146
url: https://leetcode.com/problems/lru-cache/
level: medium
solution: get O(1): hash map lookup,
            put O(1): hash map set, and hash map delete, use double linked list to keep track.

#lruCache
 */

public class LRUCache {
    int currCapacity = 0, capacity;
    HashMap<Integer, Integer> table;
    HashMap<Integer, ListNode> nodeMap;
    ListNode begin, end;

    public LRUCache(int capacity) {
        table = new HashMap<Integer, Integer>();
        nodeMap = new HashMap<Integer, ListNode>();
        begin = new ListNode(-1);
        end = new ListNode(-1);
        begin.next = end; end.prev = begin;
        this.capacity = capacity;
        this.currCapacity = 0;
    }

    public int get(int key) {
        //maintain cap
        if(this.table.containsKey(key)){
            ListNode keyNode = nodeMap.get(key);
            removeNode(keyNode);
            addToBegin(keyNode);
            return this.table.get(key);
        }
        else return -1;
    }


    public void put(int key, int value) {
        //maintain cap, if new key, add it to the list, remove the last element, unset the hash map.
        //if old key, just shuffle and add the key to the beginning.
        if(this.table.containsKey(key)){
            ListNode keyNode = nodeMap.get(key);
            removeNode(keyNode);
            addToBegin(keyNode);
        }
        else{
            ListNode newKeyNode = new ListNode(key);
            nodeMap.put(key, newKeyNode);
            addToBegin(newKeyNode);

            currCapacity +=1;

            if(currCapacity > capacity){
                int delKey = removeFromEnd();
                this.table.remove(delKey);
                currCapacity--;
            }
        }

        table.put(key, value);
    }

    private void removeNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToBegin(ListNode node){
        ListNode temp = begin.next;
        begin.next = node;
        node.prev = begin;
        node.next = temp;
        temp.prev = node;
    }

    private int removeFromEnd(){
        ListNode temp = end.prev.prev;
        int delKey = end.prev.val;

        this.nodeMap.remove(delKey);
        end.prev = temp;
        temp.next = end;

        return delKey;
    }

    public static void main(String[] args){
        LRUCache cache = new LRUCache( 1 /* capacity */ );

        /*
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4


        /*
        cache.put(2, 1);
        cache.put(1, 1);
        cache.get(2);
        cache.put(4, 1);
        cache.get(1);
        cache.get(2);
        */

        /*
        System.out.println(cache.get(2));
        cache.put(2,6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        */

        /*
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);    //evict 1
        cache.get(4);       //return 4
        cache.get(3);       //return 3
        cache.get(2);       //return 2
        cache.get(1);       //return -1
        cache.put(4, 4);
        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.get(4);
        cache.get(5);
        */

        cache.put(2, 1);
        System.out.println(cache.get(2));
        cache.put(3, 2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
    }

    static
    class ListNode
    {
        public int val;
        public ListNode prev;
        public ListNode next;
        public ListNode(int v){this.val = v;}
    }
}


