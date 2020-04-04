package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*
number: 380
url: https://leetcode.com/problems/insert-delete-getrandom-o1/
level: medium
solution: insert, delete, return random in O(1)
        Array insert O(1)
        HashMap used in delete O(1), but the ordering messed up
 */


public class InsertDeleteGetRandomO1 {
    public static void main(String[] args) {

         //Your RandomizedSet object will be instantiated and called as such:
         RandomizedSet randomSet = new RandomizedSet();
        // Inserts 1 to the set. Returns true as 1 was inserted successfully.
        randomSet.insert(0);

        // Returns false as 2 does not exist in the set.
        randomSet.remove(0);

        // Inserts 2 to the set, returns true. Set now contains [1,2].
        randomSet.insert(-1);
        randomSet.remove(0);
        //randomSet.remove(1);
        int r = randomSet.getRandom();
        System.out.format("Get: %d\n",r);

    }

    static
    class RandomizedSet {

        HashMap<Integer, Integer> hm;
        ArrayList<Integer> list;
        int lastIdx = 0;
        Random r= new Random();

        /** Initialize your data structure here. */
        public RandomizedSet() {
            hm = new HashMap<>();
            list = new ArrayList<>();
        }

        private void addElem(int val){
            if(lastIdx < list.size())
                list.set(lastIdx++, val);
            else {
                list.add(val);
                ++lastIdx;
            }
        }
        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(hm.containsKey(val))
                return false;

            addElem(val);
            hm.put(val, lastIdx-1);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!hm.containsKey(val))
                return false;

            int idx = hm.get(val);
            int lastElem = list.get(lastIdx-1);
            hm.remove(val);

            if(val != lastElem) { //only element are difference we do a swap
                list.set(idx, lastElem);
                hm.put(lastElem, idx);
            }
            --lastIdx;

            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get(r.nextInt(lastIdx));
        }
    }
}
