package ctci.hard;
import java.util.*;

public class MultiSearch {
    public HashSet<String> multiSearch(String b, String[] T){

        //create trie
        TrieNode2 root = new TrieNode2();
        for(int i=0; i<T.length; ++i){
            TrieNode2 ptr = root;
            for(int j=0; j<T[i].length(); ++j){
                char cs = T[i].charAt(j);
                int ci = cs-'a';
                if(ptr.children[ci] == null) {
                    ptr.children[ci] = new TrieNode2();
                    ptr.children[ci].debug = (ptr.debug == null ? "" : ptr.debug) + cs;
                }
                ptr = ptr.children[ci];
            }
            ptr.outIndex.add(T[i]);
        }
        //add automaton
        buildAutomaton(root);

        //do the searching
        TrieNode2 ptr = root;
        HashSet<String> result = new HashSet<>();
        for(int i=0; i<b.length(); ++i){
            char cs = b.charAt(i);
            int ci = cs-'a';
            while(ptr != null && ptr.children[ci] == null){
                ptr = ptr.f;
            }
            if(ptr == null) ptr = root;
            else ptr = ptr.children[ci];

            //System.out.println("At " + ptr.debug);

            result.addAll(ptr.outIndex);
        }

        return result;
    }

    private void buildAutomaton(TrieNode2 root){
        Queue<TrieNode2> q = new ArrayDeque<>();

        root.f = null;
        for(int i=0; i<26; ++i){
            if(root.children[i] == null) continue;
            root.children[i].f = root;
            q.offer(root.children[i]);
        }

        while(!q.isEmpty()){
            TrieNode2 u = q.poll();

            for(int i=0; i<26; ++i){
                if(u.children[i] == null) continue;

                //find the failure node
                TrieNode2 failure = u.f;
                while(failure != null && failure.children[i] == null){
                    failure = failure.f;
                }
                if(failure == null) u.children[i].f = root;
                else{
                    u.children[i].f = failure.children[i];
                    u.outIndex.addAll(u.children[i].f.outIndex);
                }

                q.offer(u.children[i]);
            }
        }
    }




    public static void main(String[] args) {
        String b = "mississippi";
        String[] T = new String[]{"is", "ppi", "hi", "sis", "i", "ssippi"};

        MultiSearch test = new MultiSearch();
        System.out.println(test.multiSearch(b, T));
    }
}

class TrieNode2{
    List<String> outIndex;
    TrieNode2[] children;
    TrieNode2 f;

    String debug;

    public TrieNode2(){
        children = new TrieNode2[26];
        outIndex = new ArrayList<>();
    }
}