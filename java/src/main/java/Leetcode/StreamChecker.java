package Leetcode;

import java.util.*;

public class StreamChecker {


    public static void main(String[] args){
        String[] words = {"cd", "f", "kl"};
        StreamChecker sol = new StreamChecker(words);
        System.out.println(sol.query('a'));
        System.out.println(sol.query('b'));
        System.out.println(sol.query('c'));
        System.out.println(sol.query('d'));
        System.out.println(sol.query('e'));
        System.out.println(sol.query('f'));
        System.out.println(sol.query('g'));
        System.out.println(sol.query('h'));
        System.out.println(sol.query('i'));
        System.out.println(sol.query('j'));
        System.out.println(sol.query('k'));
        System.out.println(sol.query('l'));
    }

    TrieNode root;
    TrieNode ptr;

    public StreamChecker(String[] words) {
        root = new TrieNode();

        buildTrie(words);
        buildAutomaton();

        ptr = root;
    }

    public boolean query(char letter) {
        int li = letter - 'a';
        while(ptr!= null && ptr.children[li] == null){
            ptr = ptr.f;
        }
        ptr = ptr == null ? root : ptr.children[li];

        //System.out.println("Current location: " + ptr.debug);

        return ptr.outIndex.size() > 0;
    }

    private void buildTrie(String[] words){
        TrieNode ptr;
        //root.debug ="";
        for(int i=0; i<words.length; ++i){
            ptr = root;

            for(int j=0; j<words[i].length(); ++j){
                char cs= words[i].charAt(j);
                int ci = cs-'a';
                if(ptr.children[ci] == null) {
                    ptr.children[ci] = new TrieNode();
                    //ptr.children[ci].debug = words[i].substring(0, j+1);
                }
                ptr = ptr.children[ci];
            }

            ptr.outIndex.add(i);
        }
    }

    private void buildAutomaton(){
        Queue<TrieNode> q = new ArrayDeque<>();

        for(int i=0; i<26; ++i){
            if(root.children[i] != null){
                root.children[i].f = root;
                q.offer(root.children[i]);
            }
        }

        while(!q.isEmpty()){
            TrieNode u = q.poll();

            for(int i= 0; i<26; ++i){
                TrieNode v = u.children[i];
                if(v != null){
                    TrieNode failure = u.f;

                    while(failure != null && failure.children[i] == null)
                        failure = failure.f;

                    failure = failure ==null ? root: failure.children[i];
                    v.f = failure;

                    v.outIndex.addAll(failure.outIndex);

                    q.offer(v);
                }
            }
        }
    }
}

class TrieNode{

    TrieNode[] children;
    List<Integer> outIndex;
    TrieNode f;

    String debug;

    public TrieNode(){
        children = new TrieNode[26];
        outIndex = new ArrayList<Integer>();
        f = null;
    }
}

