package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
number: 139
url: https://leetcode.com/problems/word-break/
level: medium
solution: build a trie, and if I reach end, I go on, or reach the other end if there is one.
 */

public class WordBreak {
    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> wordDict = Arrays.asList("aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa","ba");

        Solution sol = new Solution();
        System.out.println(sol.wordBreak(s, wordDict));

    }

    static
    class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            Trie t = new Trie();
            for(String word : wordDict){
                t.insert(word);
            }

            return t.search(s);
        }
    }

    static
    class Trie{
        // Alphabet size (# of symbols)
        static final int ALPHABET_SIZE = 26;

        // trie node
        static class TrieNode
        {
            TrieNode[] children = new TrieNode[ALPHABET_SIZE];

            // isEndOfWord is true if the node represents
            // end of a word
            boolean isEndOfWord;

            TrieNode(){
                isEndOfWord = false;
                for (int i = 0; i < ALPHABET_SIZE; i++)
                    children[i] = null;
            }
        };

        TrieNode root;
        TrieNode curr;

        public Trie(){ root = new TrieNode(); curr = root;  }


        public void insert(String key){
            int level;
            int length = key.length();
            int index;

            TrieNode pCrawl = root;

            for(level =0; level< length; ++level){
                index = key.charAt(level) - 'a';
                if(pCrawl.children[index] == null)
                    pCrawl.children[index] = new TrieNode();

                pCrawl = pCrawl.children[index];
            }

            pCrawl.isEndOfWord = true;
        }

        HashMap<String, Boolean> dp;
        public boolean search(String next) {
            /*  bfs will have MLE, so giveup
            ArrayList<TrieNode> nodes = new ArrayList<TrieNode>();
            nodes.add(root);

            for(int i=0; i<next.length(); ++i){
                char c = next.charAt(i);
                ArrayList<TrieNode> newState = new ArrayList<TrieNode>();
                for(TrieNode node: nodes){
                    TrieNode nextNode = node.children[c-'a'];
                    if(nextNode != null){
                        if(nextNode.isEndOfWord){
                            newState.add(root);
                        }
                        newState.add(nextNode);
                    }
                }

                nodes = newState;
            }

            for(TrieNode n: nodes){
                if(n.isEndOfWord) return true;
            }
            return false;
            */
            dp = new HashMap<String, Boolean>();
            return searchDfs(next, root);
        }

        //beware of curr_t

        public boolean searchDfs(String next, TrieNode curr_t){
            TrieNode curr = curr_t;

            if(curr_t==this.root && dp.containsKey(next)){
                System.out.println("DP "+ next + " " + curr_t);
                return dp.get(next);
            }

            System.out.println(next + " " + curr);

            for(int i=0; i<next.length(); ++i) {
                char c = next.charAt(i);

                TrieNode nextNode = curr.children[c - 'a'];
                if (nextNode == null) {
                    if(curr_t == this.root)
                        dp.put(next, false);
                    return false;
                }
                else {
                    if (nextNode.isEndOfWord) {
                        boolean result = searchDfs(next.substring(i + 1), root) || searchDfs(next.substring(i + 1), nextNode);
                        if(curr_t == this.root)
                            dp.put(next, result);
                        return result;
                    }
                }
                curr = nextNode;
            }

            boolean result = curr.isEndOfWord;
            if(curr_t == this.root)
                dp.put(next, result);
            return result;
        }

    }
}

