package ctci.moderate;
import java.util.*;

public class LRUCache<K, V> {

    ListNode<K, V> head;
    ListNode<K, V> tail;
    HashMap<K, ListNode<K, V>> map;
    int capacity;
    int size;

    public LRUCache(int cap){
        head = new ListNode<>() ;
        tail = new ListNode<>();
        head.next = tail;
        tail.prev = head;
        map = new HashMap<K, ListNode<K, V>>();
        capacity = cap;
        size = 0;
    }

    public void put(K key, V value){
        //if this is an update
        if(map.containsKey(key)){
            ListNode<K, V> updateN = map.get(key);
            updateN.value = value;
            moveNodeToEnd(updateN);
        }
        else{
            if(size == capacity){
                //evict the first node
                ListNode<K, V> evict = head.next;
                head.next = head.next.next;
                head.next.prev = head;
                map.remove(evict.key);
                --size;
            }
            ListNode<K, V> newNode = new ListNode(key, value);
            map.put(key, newNode);

            newNode.prev = tail.prev;
            newNode.next = tail;
            tail.prev.next = newNode;
            tail.prev = newNode;
            ++size;
        }
    }

    private void moveNodeToEnd(ListNode<K,V> node){

        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;

    }

    public V get(K key){
        //now we also need
        ListNode<K, V> node = map.getOrDefault(key, null);
        if(node == null) return null;
        moveNodeToEnd(node);

        return map.get(key).value;
    }

    public static void main(String[] args) {
        //test copy from
        LRUCache<Integer, Integer> test = new LRUCache<>(2);
        test.put(1, 1);
        test.put(2, 2);
        System.out.println(test.get(1));
        test.put(3, 3);
        System.out.println(test.get(2));
        test.put(4, 4);
        System.out.println(test.get(1));
        System.out.println(test.get(3));
        System.out.println(test.get(4));

    }

}
class ListNode<K, V>{
    public ListNode prev;
    public ListNode next;
    public K key;
    public V value;

    public ListNode(K key, V value){
        this.key = key;
        this.value = value;
    }

    public ListNode(){

    }
}
