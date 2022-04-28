package Leetcode.Contest278;

import java.util.Arrays;

public class D {

    boolean[][] connect;
    String[] words;
    boolean[] visited;
    public int[] groupStrings(String[] words) {
        this.words = words;
        connect = new boolean[words.length][words.length];
        for(int i=0; i<words.length; ++i){
            for(int j=0; j<i; ++j){
                boolean cc = isConnected(words[i], words[j]);
                connect[i][j] = cc;
                connect[j][i] = cc;
            }
        }

        //find the number of group
        visited = new boolean[words.length];
        Arrays.fill(visited, false);

        int maxGroupSize = 0;
        int totalGroup = 0;
        for(int i=0; i<words.length; ++i){
            if(!visited[i]){
                int gsize = dfs(i);
                ++totalGroup;
                maxGroupSize = Math.max(gsize,maxGroupSize);
            }
        }

        return new int[]{totalGroup, maxGroupSize};
    }

    public int dfs(int u){
        visited[u] = true;
        int gsize= 1;
        for(int i=0; i<words.length; ++i){
            if(connect[u][i] && !visited[i])
                gsize += dfs(i);
        }

        return gsize;
    }

    int[] ec = new int[26];
    private boolean isConnected(String a, String b){
        if(a.compareTo(b) == 0) return true;
        Arrays.fill(ec, 0);
        //if their len diff by 1. check
        int lenDiff = Math.abs(a.length()-b.length());
        if( lenDiff == 1) {
            String minS, maxS;
            if(a.length() < b.length()){
                minS = a; maxS = b;
            }
            else{
                minS = b; maxS = a;
            }

            for(int i=0; i<maxS.length(); ++i)
                ec[maxS.charAt(i)-'a']++;
            for(int i=0; i<minS.length(); ++i) {
                if (ec[minS.charAt(i)-'a'] == 0)
                    return false;
            }
            return true;
        }
        else if(lenDiff == 0){
            int change = 0;

            for(int i=0; i<a.length(); ++i)
                ec[a.charAt(i)-'a']++;
            for(int i=0; i<b.length(); ++i)
                ec[b.charAt(i)-'a']--;

            boolean have_1=false, have1 = false;
            for(int i=0; i<26; ++i){
                if(ec[i] == -1) have_1=true;
                else if(ec[i] == 1) have1=true;
            }

            return have_1 && have1;
        }
        return false;

    }



    public static void main(String[] args){

    }
}
