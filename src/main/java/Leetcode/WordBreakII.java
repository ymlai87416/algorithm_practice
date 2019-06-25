package Leetcode;

import java.util.*;

public class WordBreakII {
    public static void main(String[] args) {
        //String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        //List<String> wordDict = Arrays.asList("aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa", "ba");

        //String s = "catsandog";
        //List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");

        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> wordDict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");

        Solution sol = new Solution();
        System.out.println(sol.wordBreak(s, wordDict));

    }

    static
    class Solution {
        public List<String> wordBreak(String s, List<String> wordDict) {
            Trie t = new Trie();
            for (String word : wordDict) {
                t.insert(word);
            }

            List<String> a =  t.search(s);
            if(a == null)
                return Collections.EMPTY_LIST;
            else
                return a;
        }
    }

    static
    class Trie {
        // Alphabet size (# of symbols)
        static final int ALPHABET_SIZE = 26;

        // trie node
        static class TrieNode {
            TrieNode[] children = new TrieNode[ALPHABET_SIZE];

            // isEndOfWord is true if the node represents
            // end of a word
            boolean isEndOfWord;

            String word;

            TrieNode() {
                isEndOfWord = false;
                for (int i = 0; i < ALPHABET_SIZE; i++)
                    children[i] = null;
            }
        }

        ;

        TrieNode root;
        TrieNode curr;

        public Trie() {
            root = new TrieNode();
            curr = root;
        }


        public void insert(String key) {
            int level;
            int length = key.length();
            int index;

            TrieNode pCrawl = root;

            for (level = 0; level < length; ++level) {
                index = key.charAt(level) - 'a';
                if (pCrawl.children[index] == null)
                    pCrawl.children[index] = new TrieNode();

                pCrawl = pCrawl.children[index];
            }

            pCrawl.isEndOfWord = true;
            pCrawl.word = key;
        }

        HashMap<String, List<String>> dp;

        public List<String> search(String next) {
            dp = new HashMap<>();
            return searchDfs(next, root);
        }

        //beware of curr_t

        public List<String> searchDfs(String next, TrieNode curr_t) {
            TrieNode curr = curr_t;

            if (curr_t == this.root && dp.containsKey(next)) {
                return dp.get(next);
            }

            //System.out.println(next + " " + curr);

            for (int i = 0; i < next.length(); ++i) {
                char c = next.charAt(i);

                TrieNode nextNode = curr.children[c - 'a'];
                if (nextNode == null) {
                    //cannot make a list
                    List<String> result = null;
                    if (curr_t == this.root)
                        dp.put(next, result);
                    return result;
                } else {
                    if (nextNode.isEndOfWord) {
                        ArrayList<String> r;
                        List<String> r_new = searchDfs(next.substring(i + 1), root);
                        List<String> r_cont = searchDfs(next.substring(i + 1), nextNode);

                        if (r_new == null && r_cont == null) {
                            r = null;
                            dp.put(next, r);
                        }
                        else {
                            r = new ArrayList<>();
                            if(r_new != null)
                                for (String s : r_new) {
                                    r.add(nextNode.word + " " + s);
                                }

                            if(r_cont != null)
                                r.addAll(r_cont);

                            if (curr_t == this.root)
                                dp.put(next, r);
                        }
                        return r;
                    }
                }
                curr = nextNode;
            }

            boolean result = curr.isEndOfWord;
            List<String> r;
            if(result)
                r = Arrays.asList(curr.word);
            else
                r = null;

            if (curr_t == this.root)
                dp.put(next, r);

            return r;
        }

    }
}
