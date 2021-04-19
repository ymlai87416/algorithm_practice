package Leetcode;

import java.util.*;

/**
number: 127
url: https://leetcode.com/problems/word-ladder/
level: medium
solution: this is a graph.

#bfs

**/

public class WordLadder {
    public static void main(String[] args) {
        String beginWord = "a";
        String endWord = "b";
        List<String> wordList = Arrays.asList("a","b","c");

        Solution sol = new Solution();
        System.out.println(sol.ladderLength(beginWord, endWord, wordList));

    }

    static
    class Solution {

        //this is BFS and it is O(MN), implement bidirectional BFS or floyd O(n^3)??
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            List<List<Integer>> adjList = new ArrayList<>();
            for(int i=0; i<wordList.size(); ++i){
                adjList.add(new ArrayList<>());
            }

            int target = -1;
            for(int i=0; i<wordList.size(); ++i){
                if(wordList.get(i).compareTo(endWord) == 0) target = i;
                for(int j=i+1; j<wordList.size(); ++j){
                    int diff = wordDiff(wordList.get(i), wordList.get(j));
                    //assume u v of same length

                    if(diff == 1){
                        adjList.get(i).add(j);
                        adjList.get(j).add(i);
                    }
                }
            }

            if(target != -1) {
                int r = Integer.MAX_VALUE;

                int[] visited = new int[wordList.size()];
                for (int i = 0; i < wordList.size(); ++i) {
                    if (wordDiff(wordList.get(i), beginWord) == 1) {
                        //can one step to target
                        if(i == target) return 2;

                        //bfs here
                        for(int j=0; j<visited.length; ++j) visited[j] = -1;
                        Queue<Integer> q = new ArrayDeque<Integer>();
                        q.offer(i);
                        visited[i] = 2;

                        while(!q.isEmpty()){
                            int u = q.poll();
                            for(int v: adjList.get(u)){
                                if(visited[v] == -1){
                                    visited[v] = visited[u]+1;
                                    if(v == target)
                                        r=Math.min(r, visited[v]);
                                    q.add(v);
                                }
                            }
                        }
                    }
                }

                if(r == Integer.MAX_VALUE) return 0;
                else return r;
            }
            else
                return 0;

        }

        private int wordDiff(String a, String b){
            int diff = 0;
            for(int u=0; u<a.length(); ++u){
                if(a.charAt(u) != b.charAt(u))
                    diff += 1;
            }
            return diff;
        }
    }
}
