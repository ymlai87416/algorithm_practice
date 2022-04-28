package Leetcode.UberMock;

import java.util.*;

public class OnsiteQ3 {

    class RandomizedSet {

        HashMap<Integer, Integer> hm = null;
        int[] buffer = new int[200001];
        int ptr = 0;
        public RandomizedSet() {
            hm = new HashMap<>();
            ptr = 0;
        }

        public boolean insert(int val) {
            if(hm.containsKey(val))
                return false;
            else{
                buffer[ptr] = val;
                hm.put(val, ptr);
                ptr++;
                return true;
            }
        }

        public boolean remove(int val) {
            if(hm.containsKey(val)){
                int valIdx = hm.get(val);
                //swap on the valIdx and the last one
                if(ptr > 1){
                    int t = buffer[ptr - 1];
                    buffer[valIdx] = t;
                    hm.put(t, valIdx);
                }

                ptr--;
                hm.remove(val);

                return true;
            }
            else
                return false;
        }

        public int getRandom() {
            Random r = new Random();
            int a = r.nextInt(ptr);
            return buffer[a];
        }
    }

    public static void main(String[] args){

    }
}
