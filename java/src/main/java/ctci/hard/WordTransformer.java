package ctci.hard;
import java.util.*;

class WordTransformer{

    int[][] visited = new int[2][5_001];


    public static void main(String[] args) {

    }
}


//This is not optimized
class WordTransformer2 {
    //This is leetcode 127. Word Ladder

    public List<String> wordTransform(String[] dictionary, String from, String to){


        TrieNode root = new TrieNode();

        for(int i=0; i<dictionary.length; ++i){
            TrieNode ptr = root;

            for(int j=0; j<dictionary[i].length(); ++i){
                char cs = dictionary[i].charAt(j);
                int is = cs - 'a';

                if(ptr.children[is] == null) ptr.children[is] = new TrieNode();
                ptr= ptr.children[is];
            }

            ptr.word = dictionary[i];
        }

        //do a bidirectional BFS
        Queue<String>[] qArr  = new Queue[2];
        qArr[0] = new ArrayDeque<>();
        qArr[1] = new ArrayDeque<>();

        qArr[0].offer(from);
        qArr[1].offer(to);

        HashMap<String, String>[] dist = new HashMap[2];
        dist[0] = new HashMap<String, String>();
        dist[1] = new HashMap<String, String>();

        dist[0].put(from, null);
        dist[1].put(to, null);

        while(true){
//find the smallest queue to expand
            int use = qArr[0].size() < qArr[1].size() ? 0: 1;

            Queue<String> cur = qArr[use];
            Queue<String> next = new ArrayDeque<>();

            while(!cur.isEmpty()){
                String u = cur.poll();

                List<String> nextC = getPossibleTransform(u, 0, 1, root);

                for(String c: nextC){
                    if(dist[1-use].containsKey(c)) //we find a match
                        return trace(dist, c);
                    if(!dist[use].containsKey(c) && !dist[1-use].containsKey(c)){
                        dist[use].put(c, u);
                        next.offer(c);
                    }
                }
            }

            qArr[use] = next;
        }

    }

    private List<String> trace(HashMap<String, String>[] dist, String c){
        ArrayDeque<String> result = new ArrayDeque<>();

        result.add(c);
        String ptr = c;
        while(ptr != null){
            ptr = dist[0].get(ptr);
            result.addFirst(ptr);
        }
        ptr= c;
        while(ptr!= null){
            ptr = dist[1].get(ptr);
            result.addLast(ptr);
        }

        return new ArrayList<>(result);
    }

    private List<String> getPossibleTransform(String word, int idx, int k, TrieNode ptr){

        if(idx == word.length() && k == 0 && ptr.word != null)
            return List.of(ptr.word);

        List<String> result =  new ArrayList<>();
        char cs = word.charAt(idx);
        int is = cs-'a';
        //either do transform here
        for(int i=0; i<26; ++i){
            if(ptr.children[i] == null) continue;
            if(is == i)
                getPossibleTransform(word, idx+1, k, ptr.children[i]);
            else if(k > 0)
                getPossibleTransform(word, idx+1, k-1, ptr.children[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        
    }
}
