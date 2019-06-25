package Leetcode;

import java.util.Stack;

public class AddAndSearchWord_DataStructureDesign {
    public static void main(String[] args){
        WordDictionary dict = new WordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        System.out.println(dict.search("pad")); //-> false
        System.out.println(dict.search("bad")); //-> true
        System.out.println(dict.search(".ad")); //-> true
        System.out.println(dict.search("b..")); //-> true
    }

    static
    class WordDictionary {

        private Trie trie;
        /** Initialize your data structure here. */
        public WordDictionary() {
            trie = new Trie();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            trie.insert(word);
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return trie.searchWithRegex(word);
        }
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

        public boolean searchWithRegexHelper(String word, int ptr, Node t){
            if(word.length() == ptr)
                return t.isTerminate;
            if(word.charAt(ptr) == '.'){
                boolean result;
                for(int i=0; i<t.next.length; ++i){
                    if(t.next[i] != null) {
                        result = searchWithRegexHelper(word, ptr+1, t.next[i]);
                        if(result)
                            return true;
                    }
                }

                return false;
            }
            else{
                char c = word.charAt(ptr);
                t = t.next[c-'a'];
                if(t == null)
                    return false;
                else
                    return searchWithRegexHelper(word, ptr+1, t);
            }
        }

        public boolean searchWithRegex(String word){
            return searchWithRegexHelper(word, 0, root);
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
