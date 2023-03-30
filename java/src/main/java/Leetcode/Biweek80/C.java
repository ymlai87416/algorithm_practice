package Leetcode.Biweek80;

import java.util.*;

class C {
    String s;
    String sub;
    HashMap<Character, Set<Character>> mh;
    public boolean matchReplacement(String s, String sub, char[][] mappings) {

        this.s = s;
        this.sub = sub;
        mh = new HashMap<>();
        for(int i=0; i<mappings.length; ++i) {
            if(!mh.containsKey(mappings[i][0]))
                mh.put(mappings[i][0], new HashSet<>());
            mh.get(mappings[i][0]).add(mappings[i][1]);
        }

        List<Integer> ptr1 = new ArrayList<>();
        List<Integer> ptr2 = new ArrayList<>();

        ptr1.add(0);

        for(int i=0; i<s.length(); ++i){
            char x = s.charAt(i);
            ptr2.clear();

            for(int j=0; j<ptr1.size(); ++j){
                int ji = ptr1.get(j);
                char subc = sub.charAt(ji);

                if(mh.get(subc).contains(x)) {
                    int nji = ji+1;
                    if(nji == sub.length()) return true;
                    ptr2.add(nji);
                }
            }

            ptr2.add(0);

            //swap
            List<Integer> tt = ptr1;
            ptr1 = ptr2;
            ptr2 = tt;
        }

        return false;
    }
/*
    private void buildTrie(TrieNode root, int idx){
        if(idx >= sub.length()){
            root.tt = true;
            return;
        }
        char x = sub.charAt(idx);
        if(mh.containsKey(x)){
            List<Character> x2l= mh.get(x);
            for(Character x2: x2l) {
                TrieNode n1 = new TrieNode(x2);
                root.children.put(x2, n1);

                buildTrie(n1, idx + 1);
            }
        }
        TrieNode n2 = new TrieNode(x);
        root.children.put(x, n2);
        buildTrie(n2, idx+1);
    }*/

    public static void main(String[] args) {
        /*
        "eeeegeeeegeeeegeeeegeeeegeeeegeeeeg"
"eeeee"
[["e","a"],["e","b"],["e","c"],["e","d"],["e","f"],["e","h"],["e","i"],["e","j"],["e","k"],["e","l"],["e","m"],["e","n"],["e","o"],["e","p"],["e","q"],["e","r"],["e","s"],["e","t"],["e","u"],["e","v"],["e","w"],["e","x"],["e","y"],["e","z"],["e","0"],["e","1"],["e","2"],["e","3"],["e","4"],["e","5"],["e","6"],["e","7"],["e","8"],["e","9"],["g","a"],["g","b"],["g","c"],["g","d"],["g","f"],["g","h"],["g","i"],["g","j"],["g","k"],["g","l"],["g","m"],["g","n"],["g","o"],["g","p"],["g","q"],["g","r"],["g","s"],["g","t"],["g","u"],["g","v"],["g","w"],["g","x"],["g","y"],["g","z"],["g","0"],["g","1"],["g","2"],["g","3"],["g","4"],["g","5"],["g","6"],["g","7"],["g","8"],["g","9"]]
         */
        C c = new C();
        char[][] tt;
        tt = new char[][]{{'e','a'},{'e','b'},{'e','c'},{'e','d'},{'e','f'},{'e','h'},{'e','i'},{'e','j'},{'e','k'},{'e','l'},{'e','m'},{'e','n'},{'e','o'},{'e','p'},{'e','q'},{'e','r'},{'e','s'},{'e','t'},{'e','u'},{'e','v'},{'e','w'},{'e','x'},{'e','y'},{'e','z'},{'e','0'},{'e','1'},{'e','2'},{'e','3'},{'e','4'},{'e','5'},{'e','6'},{'e','7'},{'e','8'},{'e','9'},{'g','a'},{'g','b'},{'g','c'},{'g','d'},{'g','f'},{'g','h'},{'g','i'},{'g','j'},{'g','k'},{'g','l'},{'g','m'},{'g','n'},{'g','o'},{'g','p'},{'g','q'},{'g','r'},{'g','s'},{'g','t'},{'g','u'},{'g','v'},{'g','w'},{'g','x'},{'g','y'},{'g','z'},{'g','0'},{'g','1'},{'g','2'},{'g','3'},{'g','4'},{'g','5'},{'g','6'},{'g','7'},{'g','8'},{'g','9'}};
        System.out.println(c.matchReplacement("eeeegeeeegeeeegeeeegeeeegeeeegeeeeg", "eeeee", tt));
    }
}
/*

class TrieNode{
    HashMap<Character, TrieNode> children;
    char c;
    boolean tt;

    public TrieNode(char c){
        children = new HashMap<>();
        this.c = c;
        this.tt = false;
    }

}*/