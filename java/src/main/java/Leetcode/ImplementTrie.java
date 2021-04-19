package Leetcode;

/*
problem: https://leetcode.com/problems/implement-trie-prefix-tree/
level: medium
solution: this is just a trie implementation

#trie

 */

public class ImplementTrie {
    public static void main(String[] args){
        int numCourses = 2;
        int[][] prerequisites = {
                {0, 1}
        };

        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }

    static
    class Trie {
        Node root;
        /** Initialize your data structure here. */
        public Trie() {
            root = new Node(' ');
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node t = root;
            for(int i=0; i<word.length(); ++i){
                char c = word.charAt(i);
                if(t.next[c-'a'] == null){
                    t.next[c-'a'] = new Node(c);
                }
                t = t.next[c-'a'];
            }
            t.isTerminate = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node t = root;

            for(int i=0; i<word.length(); ++i){
                char c = word.charAt(i);
                t = t.next[c-'a'];
                if(t == null) return false;
            }

            return t.isTerminate;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node t = root;

            for(int i=0; i<prefix.length(); ++i){
                char c = prefix.charAt(i);
                t = t.next[c-'a'];
                if(t == null) return false;
            }

            return true;
        }
    }

    static
    class Node{
        Node[] next = new Node[26];
        char val;
        boolean isTerminate;

        public Node(char v){
            this.val = v;
            this.isTerminate = false;
        }
    }

}
