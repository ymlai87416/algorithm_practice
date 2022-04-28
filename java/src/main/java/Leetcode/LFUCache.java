package Leetcode;

import java.util.*;

public class LFUCache {

    static class FreqMap{
        int freq;
        Queue<Entry> queue;
        public FreqMap(int freq){
            this.freq = freq;
            queue = new ArrayDeque<Entry>();
        }

    }

    static class Entry{
        int key;
        int value;
        int freq;
        public Entry(int key, int value){
            freq = 1;
            this.key = key;
            this.value = value;
        }
    }

    HashMap<Integer, Entry> lookup;
    //freq alway 1 and appends to front. O(1)
    //freq update and append to back if we have a bigger frequency
    //lookup linked list can be done by hashmap
    LinkedList<FreqMap> queue;
    HashMap<Integer, FreqMap> freqLookup;

    int capacity;
    int curSize;
    //keep frequency count, and also the last read
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.curSize = 0;
        lookup = new HashMap<>();
        queue = new LinkedList<>();
        freqLookup = new HashMap<>();
    }

    //O(1)
    public int get(int key) {
        if(capacity == 0)
            return -1;

        //hash map for O(1) access, update the frequency.
        Entry e= lookup.get(key);
        if(e == null) return -1;
        e.freq++;
        if(!freqLookup.containsKey(e.freq)){
            FreqMap fm = new FreqMap(e.freq);

            freqLookup.put(e.freq, fm);
            queue.addLast(fm);
        }
        FreqMap fm = freqLookup.get(e.freq);
        fm.queue.add(e);

        return e.value;
    }

    //O(1)
    public void put(int key, int value) {

        if(capacity == 0) return;

        if(!lookup.containsKey(key)){

            //check the size and start to pop
            if(curSize == capacity){
                boolean deleted = false;
                while(!deleted){
                    FreqMap fmap = queue.getFirst();
                    while(!fmap.queue.isEmpty()){
                        Entry re = fmap.queue.poll();
                        if(re.freq == fmap.freq) {
                            System.out.println("Remove: " + re.key + " " + re.freq);
                            lookup.remove(re.key);
                            curSize--;
                            deleted = true;
                            break;
                        }
                    }
                    freqLookup.remove(fmap.freq);
                    queue.removeFirst();
                }
            }

            Entry e = new Entry(key, value);
            lookup.put(key, e);

            if(!freqLookup.containsKey(e.freq)){
                FreqMap fm = new FreqMap(e.freq);
                freqLookup.put(e.freq, fm);
                queue.addFirst(fm); //this freq is 1, must be at front.
            }

            freqLookup.get(e.freq).queue.add(e);
            curSize += 1;
        }
        else{
            //already exists, no need to pop;
            Entry e= lookup.get(key);
            e.value = value;
            e.freq++;
            if(!freqLookup.containsKey(e.freq)){
                FreqMap fm = new FreqMap(e.freq);
                freqLookup.put(e.freq, fm);
                queue.addLast(fm);
            }
            FreqMap fm = freqLookup.get(e.freq);
            fm.queue.add(e);

            return;
        }
    }
    static String[] func = {"LFUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"};
    static int[][] data = {{10},{10,13},{3,17},{6,11},{10,5},{9,10},{13},{2,19},{2},{3},{5,25},{8},{9,22},{5,5},{1,30},{11},{9,12},{7},{5},{8},{9},{4,30},{9,3},{9},{10},{10},{6,14},{3,1},{3},{10,11},{8},{2,14},{1},{5},{4},{11,4},{12,24},{5,18},{13},{7,23},{8},{12},{3,27},{2,12},{5},{2,9},{13,4},{8,18},{1,7},{6},{9,29},{8,21},{5},{6,30},{1,12},{10},{4,15},{7,22},{11,26},{8,17},{9,29},{5},{3,4},{11,30},{12},{4,29},{3},{9},{6},{3,4},{1},{10},{3,29},{10,28},{1,20},{11,13},{3},{3,12},{3,8},{10,9},{3,26},{8},{7},{5},{13,17},{2,27},{11,15},{12},{9,19},{2,15},{3,16},{1},{12,17},{9,1},{6,19},{4},{5},{5},{8,1},{11,7},{5,2},{9,28},{1},{2,2},{7,4},{4,22},{7,24},{9,26},{13,28},{11,26}};

    public static void main(String[] args){
        LFUCache c = null;
        int get = 0;
        for(int i=0; i<func.length; ++i){
            String  f = func[i];
            int[] d = data[i];

            if(f.compareTo("LFUCache") == 0){
                System.out.println("Create size: " + d[0]);
                c = new LFUCache(d[0]);
            }
            else if(f.compareTo("put") == 0){
                System.out.println("put: " + d[0] + ", " + d[1]);
                c.put(d[0], d[1]);
            }
            else if(f.compareTo("get") == 0){
                ++get;
                System.out.println("get: " + d[0]);
                System.out.println(c.get(d[0]));
            }
        }

        /*
        LFUCache c = new LFUCache(2);
        c.put(1, 1);
        c.put(2, 2);
        System.out.println(c.get(1));
        c.put(3,3);
        System.out.println(c.get(2));
        System.out.println(c.get(3));
        c.put(4,4);
        System.out.println(c.get(1));
        System.out.println(c.get(3));
        System.out.println(c.get(4));
         */
    }

}
