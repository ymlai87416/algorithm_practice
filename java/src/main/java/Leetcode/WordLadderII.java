package Leetcode;

import java.util.*;

/**
number: 126
problem: https://leetcode.com/problems/word-ladder-ii/
level: hard
solution: word ladder + backtrack

#array #string #backtracking #bfs

 **/

public class WordLadderII {

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");

        Solution sol = new Solution();
        System.out.println(sol.findLadders(beginWord, endWord, wordList));

    }

    static
    class Solution {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            return bfsHelper(beginWord, endWord, wordList);
        }

        private List<List<String>> floydHelper(String beginWord, String endWord, List<String> wordList){
            /*
            ArrayList<String> newWordList = new ArrayList<>(wordList);
            int beginIdx = -1;
            int targetIdx = -1;
            for(int i=0; i<newWordList.size(); ++i) {
                if(newWordList.get(i).compareTo(endWord) == 0) targetIdx = i;
                if(newWordList.get(i).compareTo(beginWord) == 0) beginIdx = i;
            }
            if(beginIdx == -1) {
                newWordList.add(beginWord);
                beginIdx = newWordList.size()-1;
            }

            int wordLen = newWordList.size();
            int[][] adjMatrix = new int[wordLen][wordLen];

            for(int i=0; i<wordLen; ++i) {

                adjMatrix[i][i] = 1;
            }

            for(int k=0; k<wordLen; ++k) {
                for (int i = 0; i < wordLen; ++i) {
                    for (int j = 0; j < wordLen; ++j) {

                    }
                }
            }
            */
            return null;
        }


        private List<List<String>> bfsHelper(String beginWord, String endWord, List<String> wordList) {
            ArrayList<String> newWordList = new ArrayList<>(wordList);
            int beginIdx = -1;
            int targetIdx = -1;
            for(int i=0; i<newWordList.size(); ++i) {
                if(newWordList.get(i).compareTo(endWord) == 0) targetIdx = i;
                if(newWordList.get(i).compareTo(beginWord) == 0) beginIdx = i;
            }
            if(beginIdx == -1) {
                newWordList.add(beginWord);
                beginIdx = newWordList.size()-1;
            }

            List<List<Integer>> adjList = new ArrayList<>();
            for(int i=0; i<newWordList.size(); ++i){
                adjList.add(new ArrayList<>());
            }

            for(int i=0; i<newWordList.size(); ++i){
                for(int j=i+1; j<newWordList.size(); ++j){
                    boolean isConnect = wordDiff(newWordList.get(i), newWordList.get(j)) == 1;
                    //assume u v of same length

                    if(isConnect){
                        adjList.get(i).add(j);
                        adjList.get(j).add(i);
                    }
                }
            }

            if(targetIdx != -1) {
                int r = Integer.MAX_VALUE;

                ArrayList<List<String>> result = new ArrayList<>();

                int[] visited = new int[newWordList.size()];
                for(int j=0; j<visited.length; ++j) visited[j] = -1;
                Queue<Integer> q = new ArrayDeque<Integer>();
                q.offer(beginIdx);
                visited[beginIdx] = 1;

                while(!q.isEmpty()){
                    int u = q.poll();
                    for(int v: adjList.get(u)){
                        if(visited[v] == -1){
                            visited[v] = visited[u]+1;
                            if(v == targetIdx)
                                break;
                            q.add(v);
                        }
                    }
                }

                if(visited[targetIdx] == -1) return new ArrayList<>();
                else{
                    Stack<String> st = new Stack<>();
                    List<List<String>> t = bfsBackTrack(newWordList, adjList, st, beginIdx, visited, targetIdx);
                    if(t!=null)
                        result.addAll(t);
                    }

                    return result;
            }
            else
                return new ArrayList<>();

        }

        private List<List<String>> bfsBackTrack(List<String> words, List<List<Integer>> adjList, Stack<String> stack, int u, int[] visited, int target){
            //System.out.format("%d %s %s\n", curStep, words.get(targetIdx), words.get(u));
            if(u == target){
                List<List<String>> result = new ArrayList<>();
                stack.push(words.get(u));
                result.add(new ArrayList<>(stack));
                stack.pop();
                return result;
            }

            List<List<String>> result = new ArrayList<>();

            for(int v : adjList.get(u)){
                if(visited[u]+1 == visited[v]) {
                    stack.push(words.get(u));
                    List<List<String>> t = bfsBackTrack(words, adjList, stack, v, visited, target);
                    if (t != null)
                        result.addAll(t);
                    stack.pop();
                }
            }

            return result;
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
