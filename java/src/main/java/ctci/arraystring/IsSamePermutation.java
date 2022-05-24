package ctci.arraystring;

import java.util.HashMap;

public class IsSamePermutation {
    //assumption: case senstive?
    public boolean isSamePermutation(String a, String b){

        HashMap<Character, Integer> count = new HashMap<>();

        for(int i=0; i<a.length(); ++i)
            count.put(a.charAt(i),count.getOrDefault(a.charAt(i), 0)+1);

        for(int i=0; i<b.length(); ++i)
            count.put(b.charAt(i),count.getOrDefault(b.charAt(i), 0)-1);

        for(Character c: count.keySet()){
            if(count.get(c) != 0) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        IsSamePermutation test = new IsSamePermutation();
        System.out.println(test.isSamePermutation("god", "dog"));  //true
        System.out.println(test.isSamePermutation("cat", "dog"));  //false
    }
}
