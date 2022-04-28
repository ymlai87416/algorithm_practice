package Leetcode;

import java.util.*;

public class PalindromePair {

    String[] words;
    Trie root;
    public List<List<Integer>> palindromePairs(String[] words) {
        //a word, create. from left, create from right, total 4 case

        this.words = words;

        //O(n*k*k) ~ 450M operation
        //return hashHelper();

        //build a trie, check that we find a target and remaining is a palindrome.
        return trieHelper();

    }

    private List<List<Integer>> trieHelper(){
        root = new Trie();
        HashSet<Integer> distinct = new HashSet<>();
        for(int i=0; i<words.length; ++i){
            addTrieReverse(root, i);
        }

        List<List<Integer>> result = new ArrayList<>();

        //now for each word, go traverse
        for(int i=0; i<words.length; ++i){
            //find word in order
            //case 1: word end and stop at a point. e.g. CAT-TAC
            //case 2: while traverse, stop point is found e.g CAT-OSOTAC
            //case 3: after traverse, this is a palidrone. e.g CATSOS-TAC
            System.out.println("D: " + words[i]);
            Trie ptr = root;
            for(int j=0; j<words[i].length(); ++j){

                if(ptr.idx != -1){
                    //check remaining is parlindrome.
                    boolean isP = checkPalindrome(words[i].substring(j));
                    if(isP && i != ptr.idx){
                        result.add(Arrays.asList(i, ptr.idx));
                    }
                }

                int ci = words[i].charAt(j) -'a';
                if(ptr.child[ci] == null){
                    ptr = null;
                    break;
                }
                else
                    ptr = ptr.child[ci];
            }

            if(ptr != null){
                //check if it lands on a word
                if(ptr.idx != -1  && i != ptr.idx)
                    result.add(Arrays.asList(i, ptr.idx));
                //check the remaining words is a palindrome.
                HashMap<Integer, String> nextR = getAll(ptr, "");
                for(Integer ni : nextR.keySet()){
                    String ns = nextR.get(ni);
                    if(ns.length() == 0) continue;
                    if(checkPalindrome(ns) && ni != ptr.idx){
                        result.add(Arrays.asList(i, ni));
                        if(words[i].compareTo("") == 0)
                            result.add(Arrays.asList(ni, i));
                    }
                }

            }
        }

        //now we get all the index
        return result;
    }

    private HashMap<Integer, String> getAll(Trie root, String prefix){
        HashMap<Integer, String> result = new HashMap<>();
        if(root.idx != -1){
            result.put(root.idx, prefix);
        }
        for(int i=0; i<26; ++i){
            if(root.child[i] != null){
                var sub = getAll(root.child[i], prefix+(char)('a'+i));
                for(Integer si: sub.keySet()){
                    result.put(si, sub.get(si));
                }
            }
        }
        return result;
    }

    private boolean checkPalindrome(String a){

        for(int i=0, j=a.length()-1; i<j; ++i, --j)
            if(a.charAt(i) != a.charAt(j)) return false;
        System.out.println("D " + a);
        return true;
    }

    private void addTrieReverse(Trie root, int idx){
        String w = words[idx];
        Trie ptr = root;
        for(int i=w.length()-1; i>=0; --i){
            char c = w.charAt(i);
            int ci = c2i(c);
            if(ptr.child[ci] == null)
                ptr.child[ci] = new Trie();
            ptr=ptr.child[ci];
        }
        ptr.idx = idx;
    }

    private int c2i(char c){
        return c-'a';
    }

    public static void main(String[] args){
        PalindromePair p = new PalindromePair();
        String[] s = new String[]{"a","b","c","ab","ac","aa"};
        p.palindromePairs(s);
    }

    static class Trie{
        int idx = -1;
        Trie[] child = new Trie[26];
        public Trie(){

        }
    }

}
