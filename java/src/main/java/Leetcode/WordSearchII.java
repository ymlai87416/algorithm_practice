package Leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
number: 212
url: https://leetcode.com/problems/word-search-ii/
level: hard
solution: 1. brute force apply the previous problem on a list of word,
          2. build a trie and traverse through dfs.
 */

public class WordSearchII {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words= new String[]{"oath","pea","eat","rain"};
        Solution sol = new Solution();
        System.out.println(sol.findWords(board, words));
    }

    static
    class Solution {
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        public List<String> findWords(char[][] board, String[] words) {
            Trie t = new Trie();
            for (int i = 0; i < words.length; ++i)
                t.insert(words[i]);

            HashSet<String> r = new HashSet<>();

            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[i].length; ++j) {
                    List<String> tr = dfsHelper(board, i, j, t.root);
                    r.addAll(tr);
                }
            }
            return new ArrayList<>(r);
        }

        public List<String> dfsHelper(char[][] board, int x, int y, Node root) {
            List<String> result = new ArrayList<String>();
            char c = board[x][y];

            Node nRoot = root.next[c - 'a'];
            if (nRoot != null && nRoot.isTerminate)
                result.add(nRoot.word);
            if(nRoot == null)
                return result;

            board[x][y] = ' ';
            for (int i = 0; i < 4; ++i) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;

                if (0 <= nx && nx < board.length && 0 <= ny && ny < board[nx].length && board[nx][ny] != ' ')
                    result.addAll(dfsHelper(board, nx, ny, nRoot));
            }
            board[x][y] = c;

            return result;
        }
    }

    static
    class Trie {
        public Node root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new Node(' ');
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Node t = root;
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                if (t.next[c - 'a'] == null) {
                    t.next[c - 'a'] = new Node(c);
                }
                t = t.next[c - 'a'];
            }
            t.word = word;
            t.isTerminate = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Node t = root;

            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                t = t.next[c - 'a'];
                if (t == null) return false;
            }

            return t.isTerminate;
        }

    }

    static
    class Node {
        Node[] next = new Node[26];
        char val;
        String word;
        boolean isTerminate;

        public Node(char v) {
            this.val = v;
            this.isTerminate = false;
        }
    }
}
