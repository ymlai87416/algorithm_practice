package ctci.hard;
import java.util.*;

public class LongestWord {

    public String longestWord(String[] words){

        Comparator compare = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };
        Arrays.sort(words, compare);

        Trie t = new Trie();
        String result = null;

        for(int i=0; i<words.length; ++i){
            String cur = words[i];

            if(!t.isMadeFromOthers(cur))
                t.insertWord(cur);
            else
                if(result == null || result.length() < cur.length())
                    result = cur;
        }

        return result;

    }

    public static void main(String[] args) {
        String[] data = new String[]{"cat", "banana", "dog", "nana", "walk", "walker", "dogwalker"};
        LongestWord test = new LongestWord();
        System.out.println(test.longestWord(data));
    }
}

class Trie{
    TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public void insertWord(String s){
        TrieNode ptr = root;

        for(int i=0; i<s.length(); ++i){
            char cs = s.charAt(i);
            int ci = cs-'a';

            if(ptr.children[ci] == null)
                ptr.children[ci] = new TrieNode();
			ptr = ptr.children[ci];
        }

        ptr.word = s;
    }

    private String _word;
    public boolean isMadeFromOthers(String s){
        this._word = s;

        return isMadeFromOtherHelper(0, root);
    }

    public boolean isMadeFromOtherHelper(int index, TrieNode ptr){
        if(ptr == null) return false;
        if(index == _word.length())
            return ptr.word != null;

        char cs = _word.charAt(index);
        int ci = cs-'a';
        ptr=  ptr.children[ci];

        if(ptr != null && ptr.word != null && isMadeFromOtherHelper(index+1, root))
            return true;

        return isMadeFromOtherHelper(index+1, ptr);
    }

}

class TrieNode{
    String word;
    TrieNode[] children;

    public TrieNode(){
        children = new TrieNode[26];
    }
}

