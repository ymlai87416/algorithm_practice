package Leetcode.AmazonMock;

import java.util.*;
import java.util.stream.Collectors;

public class PhoneQ3 {

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hm = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {

            String a = strs[i];
            char[] x = a.toCharArray();
            Arrays.sort(x);

            String key = new String(x);
            if(!hm.containsKey(key))
                hm.put(key, new ArrayList<>());

            hm.get(key).add(a);

        }

        return hm.values().stream().collect(Collectors.toList());
    }

    public static void main(String[] args){

    }

}
