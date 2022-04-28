package Leetcode;

import java.util.*;

public class PrefixAndSuffixSearch {

    public static void main(String[] args){
        String[] words = new String[10];
        words[0] = "cabaabaaaa";
        words[1] = "ccbcababac";
        words[2] = "bacaabccba";
        words[3] = "bcbbcbacaa";
        words[4] = "abcaccbcaa";
        words[5] = "accabaccaa";
        words[6] = "cabcbbbcca";
        words[7] = "ababccabcb";
        words[8] = "caccbbcbab";
        words[9] = "bccbacbcba";

        WordFilter wf = new WordFilter(words);

        System.out.println(wf.f("bccbacbcba","a"));
        System.out.println(wf.f("ab","abcaccbcaa"));
        System.out.println(wf.f("a","aa"));
        System.out.println(wf.f("cabaaba","abaaaa"));
        System.out.println(wf.f("cacc","accbbcbab"));
        System.out.println(wf.f("ccbcab","bac"));
        System.out.println(wf.f("bac","cba"));
        System.out.println(wf.f("ac","accabaccaa"));
        System.out.println(wf.f("bcbb","aa"));
        System.out.println(wf.f("ccbca","cbcababac"));

    }

    static class WordFilter {
        Trie root;
        String[] words;

        public WordFilter(String[] words) {
            root = new Trie();
            this.words = words;

            for(int i=0; i<words.length; ++i)
                addWord(root, i);
        }


        int temp = -1;
        public int f(String prefix, String suffix) {
            System.out.println("D" + prefix+" " + suffix);

            Trie ptr = root;
            int maxLen = Math.max(prefix.length(), suffix.length());

            for(int i=0; i<maxLen; ++i){
                char pn = i < prefix.length() ? prefix.charAt(i) : '*';
                char sn = i < suffix.length() ? suffix.charAt(suffix.length()-i-1) : '*';

                String key = ""+pn+sn;
                System.out.println("D" + key);

                if(!ptr.child.containsKey(key))
                    return -1;
                else
                    ptr = ptr.child.get(key);
            }

            //now we traverse the node to get all the
            temp = -1;
            dfs(ptr);

            return temp;
        }

        private void dfs(Trie r){
            if(r.idx != -1)
                if(temp == -1 || words[temp].compareTo(words[r.idx]) < 0)
                    temp = r.idx;

            for(String s: r.child.keySet()){
                dfs(r.child.get(s));
            }
        }

        //suffix and prefix must have at least 1 word.
        private void addWord(Trie root, int idx){
            Trie ptr = root;
            String word = words[idx];

            for(int i=0; i<word.length(); ++i){
                char p = word.charAt(i);
                char s = word.charAt(word.length()-i-1);

                String key = ""+p+s;
                if(!root.child.containsKey(key))
                    root.child.put(key, new Trie());

                root = root.child.get(key);

                //now from this, we add a prefix and suffix subbranch
                Trie prefixSub = root;
                Trie suffixSub = root;

                for(int j=i+1; j<word.length(); ++j){
                    char cp = word.charAt(j);
                    char cs = word.charAt(word.length()-j-1);

                    String preKey = cp + "*";
                    String sufKey = "*" + cs;

                    if(!prefixSub.child.containsKey(preKey))
                        prefixSub.child.put(preKey, new Trie());
                    if(!suffixSub.child.containsKey(sufKey))
                        suffixSub.child.put(sufKey, new Trie());

                    prefixSub = prefixSub.child.get(preKey);
                    suffixSub = suffixSub.child.get(sufKey);
                }

                prefixSub.idx = idx;
                suffixSub.idx = idx;
            }
            ptr.idx = idx;
        }

    }



    static class Trie{
        HashMap<String, Trie> child;
        int idx;
        public Trie(){
            idx = -1;
            child = new HashMap<>();
        }
    }
}
