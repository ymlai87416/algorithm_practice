package GoogleCodeJam.Y2011.Round1A.B;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class Solution {

    static String   FILENAME;
    static Scanner sc;
    static String   IN;
    static String   OUT;
    static PrintStream out;

    static{
        try{
            IN = "C:\\GitProjects\\algorithm_practice\\java\\src\\main\\java\\GoogleCodeJam\\Y2011\\Round1A\\B\\B-test.in";
            //IN = null;
            if(IN == null)
                sc = new Scanner(System.in);
            else
                sc = new Scanner(new File(IN));
            out = new PrintStream(System.out);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    boolean debugflag = false;
    private void debug(String s){
        if(debugflag) {
            //System.out.println(s);
            System.out.println("\033[0;34m" + s + "\033[0;30m");
        }

    }


    String[] dict = new String[10001];
    HashMap<String, Integer> order = new HashMap<>();
    int N;


    private String[] solve(int N, int M, String[] Ls) {
        String[] rr = new String[M];
        this.N = N;

        //hashmap to lookup the order
        order.clear();
        for (int i = 0; i < N; i++) {
            order.put(dict[i], i);
        }

        //then dp the result
        for (int i = 0; i < M; i++) {
            rr[i] = solveL2(Ls[i]);
        }
        return rr;
    }

    private String solveSmallL(String L){
        int ans = -1;
        String result = null;
        boolean[] ok = new boolean[N];
        for (int i = 0; i < N; i++) {
            String target = dict[i];

            for (int j = 0; j < N; j++) {
                if(dict[j].length() == target.length())
                    ok[j] = true;
                else
                    ok[j] = false;
            }
            int temp = 0;
            StringBuilder sb=new StringBuilder();
            String regExp = "";
            for (int j = 0; j < target.length(); j++) {
                sb.append('.');
            }
            regExp = sb.toString();
            for (int j = 0; j < L.length(); j++) {
                char curc = L.charAt(j);
                //find any remaining word has that char
                boolean tryc = false;
                for (int k = 0; k < N; k++) {
                    if(ok[k] && (dict[k].indexOf(curc) != -1)) {
                        tryc = true;
                        break;
                    }
                }

                if(tryc) {
                    boolean haveC = false;
                    sb = new StringBuilder();
                    for (int k = 0; k < target.length(); k++) {
                        if (target.charAt(k) == curc) {
                            sb.append(curc);
                            haveC = true;
                        }
                        else
                            sb.append(regExp.charAt(k));
                    }
                    regExp = sb.toString();

                    if(regExp.indexOf('.') == -1)
                        break;

                    String solid = "";
                    for (int k = 0; k <regExp.length(); k++) {
                        if(regExp.charAt(k)!='.')
                            solid = solid + (regExp.charAt(k));
                    }

                    //remove candidate not applicable
                    for (int k = 0; k < N; k++) {
                        if (ok[k]) {
                            if(!haveC) {
                                //error 1: forgot to filter the case where the given letter appear in other words.
                                if (dict[k].indexOf(curc) != -1)
                                    ok[k] = false;
                            }
                            else {
                                //error 2: reg expression cannot help.
                                if(ok[k]) {
                                    for (int l = 0; l < dict[k].length(); l++) {
                                        char c = dict[k].charAt(l);
                                        if(solid.indexOf(c) != -1 && regExp.charAt(l) != c)
                                            ok[k] = false;
                                        if(solid.indexOf(c) == -1 && regExp.charAt(l) != '.')
                                            ok[k] = false;
                                    }
                                }
                            }
                        }
                    }

                    if(!haveC)
                        ++temp;
                }

            }

            if(temp > ans){
                ans = temp;
                result=  target;
            }
        }

        debug(ans + " " + result);
        return result;
    }

    static class State{
        String word;
        HashSet<State>[] next = new HashSet[26];

        public State(String s){
            this.word = s;
        }

        public void addState(int idx, State s){
            if(next[idx] == null) next[idx] = new HashSet<>();
            next[idx].add(s);
        }

        public boolean equals(Object o){
            if(!(o instanceof State)) return false;
            State so = (State)o;
            return so.word.compareTo(((State) o).word) == 0;
        }
    }

    static class RetVal{
        int score;
        int dictIndex;
        String word;

        public RetVal(int score, String word){
            this.score = score;
            this.word = word;
        }

        public RetVal(int score, int id){
            this.score = score;
            this.dictIndex = id;
        }
    }

    private void generateCase(){
        int N = 5;
        int M = 5;
        Random r = new Random();
        out.format("%d %d\n", N, M);
        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                char c = (char)('a' +r.nextInt(26));
                sb.append(c);
            }
            out.println(sb.toString());
        }

        char[] t = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        ArrayList<Character> tx = new ArrayList<Character>();
        for (int i = 0; i < 26; i++) {
            tx.add(t[i]);
        }

        for (int i = 0; i < M; i++) {
            Collections.shuffle(tx);
            StringBuilder sb= new StringBuilder();
            for (int j = 0; j <26; j++) {
                sb.append(tx.get(j));
            }

            out.println(sb.toString());
        }
    }

    private String solveL2(String L){
        //for each string, get all possible configuration, and then build a tree, and then find the deepest node
        HashMap<String, State> hm = new HashMap<>();
        HashSet<State> roots = new HashSet<>();
        for (int i = 0; i < N; i++) {

            String w = dict[i];
            State next = new State(w);
            hm.put(w, next);

            for (int j = L.length()-1; j >=0; --j) {

                if(w.indexOf(L.charAt(j))!=-1){
                    w = w.replace(L.charAt(j), '.');
                    State s;
                    if(hm.containsKey(w))
                        s = hm.get(w);
                    else {
                        s = new State(w);
                        hm.put(w, s);
                    }

                    if(next != null)
                        s.addState(j, next);

                    next = s;
                }

            }

            if(!roots.contains(next))
                roots.add(next);

        }

        //now traverse all the nodes and get the answer
        RetVal result = null;
        for (State root: roots) {
            RetVal temp = traverse(root);
            //bug: check the ordering and you are done.
            if(result!=null){
                if(temp.score == result.score){
                    if(order.get(temp.word) < order.get(result.word))
                        result = temp;
                }
                else if (temp.score > result.score)
                    result = temp;
            }
            else
                result = temp;
        }

        return result.word;
    }

    private RetVal traverse(State s){
        int ss = 0;
        RetVal result = null;

        if(s.word.indexOf('.') == -1)
            return new RetVal(0, s.word);

        for (int i = 0; i < s.next.length; i++) {
            if(s.next[i] == null) continue;
            for (State sss: s.next[i]){

                RetVal temp = traverse(sss);
                temp.score += ss;
                if(result!=null){
                    if(temp.score == result.score){
                        if(order.get(temp.word) < order.get(result.word))
                            result = temp;
                    }
                    else if (temp.score > result.score)
                        result = temp;
                }
                else
                    result = temp;
            }
            ++ss;
        }

        return result;
    }

    /*

    int[] w = new int[10001];
    String[] dict2 = new String[10001];
    String L;

    static
    class Trie {
        Node root;

    public Trie() {
        root = new Node(' ');
    }


    public void insert(String word, int idx) {
        Node t = root;
        for(int i=0; i<word.length(); ++i){
            char c = word.charAt(i);
            if(t.next[c-'a'] == null){
                t.next[c-'a'] = new Node(c);
            }
            t = t.next[c-'a'];
        }
        t.isTerminate = true;
        t.dictIndex = idx;
    }


    public boolean search(String word) {
        Node t = root;

        for(int i=0; i<word.length(); ++i){
            char c = word.charAt(i);
            t = t.next[c-'a'];
            if(t == null) return false;
        }

        return t.isTerminate;
    }

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
        int dictIndex;

        public Node(char v){
            this.val = v;
            this.isTerminate = false;
        }
    }

    private String solveL(String L){
        this.L = L;

        for (int i = 0; i < N; i++) {
            w[i] = 0;
        }

        //transform the string and put them all in trie.
        HashMap<String, Integer> reverseLookup = new HashMap<>();
        for (int i = 0; i <N; i++) {
            Character[] w = new Character[dict[i].length()];
            for (int j = 0; j < dict[i].length(); j++) {
                w[j] = dict[i].charAt(j);
            }

            Arrays.sort(w, new Comparator<Character>(){
                @Override
                public int compare(Character o1, Character o2) {
                    return L.indexOf(o1) - (L.indexOf(o2));
                }
            });

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < w.length; j++) {
                if(j > 0 && w[j] == w[j-1]) continue;
                sb.append(w[j]);
            }
            dict2[i] = sb.toString();

            if(!reverseLookup.containsKey(dict2[i])){
                reverseLookup.put(dict2[i], i);
            }
        }


        Trie t = new Trie();
        //build a tire using dictionary
        for(String k : reverseLookup.keySet()) {
            t.insert(k, reverseLookup.get(k));
        }

        //preprocess L here, find out the L

        dp.clear();
        dp2.clear();
        //the state is a list of string

        RetVal result = null;
        for (int i = 0; i < N; i++) {
            String target = dict[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < target.length(); j++) {
                sb.append(".");
            }
            RetVal s = helper(sb.toString(), t.root, 0);
            if(result == null || s.score > result.score)
                result = s;
        }

        return dict[result.dictIndex];
    }



    HashMap<Node, RetVal[]> dp  =new HashMap<>();
    HashMap<String, RetVal[]> dp2 = new HashMap<>();

    private RetVal helper(String state){
        //get where is the last ptr
        //find all the matches

    }

    //this is a dp problem
    private RetVal helper2(Trie t, Node root, int ptr, String regExp){

        if(ptr== L.length())
            return null;  //should not reach here

        if(dp.containsKey(root) && dp.get(root)[ptr] != null){
            return dp.get(root)[ptr];
        }

        if(root.isTerminate)
            return new RetVal(0, root.dictIndex);

        char c = L.charAt(ptr);

        //have c, go both way, no c, go single way. (not adding score)
        RetVal s1 = null;
        RetVal s2 = null;
        if(root.next[c-'a'] == null) {
            s1 = helper(t, root, ptr + 1);  //no word have that letter, skip
        }
        else {
            s1 = helper(t, root.next[c - 'a'], ptr + 1); //choose word that have character
            s2 = helper(t, root, ptr + 1);      //choose word which does not have the character
            if(s2!=null) s2.score+=1;
        }

        RetVal result = null;
        if(s1 != null && s2!=null){
            if(s1.score > s2.score) result = s1; //return smallest
            else if(s1.score == s2.score){
                if(s1.dictIndex <= s2.dictIndex)
                    result = s1;
                else
                    result = s2;
            }
            else result = s2;
        }
        else if(s1 == null) result = s2;
        else if(s2 == null) result =  s1;


        if(dp.containsKey(root))
            dp.get(root)[ptr] = result;
        else{
            RetVal[] dpSub = new RetVal[26];
            dpSub[ptr] = result;
        }

        return result;

    }


     */

    private void run() throws Exception {
        // out = new PrintStream(new FileOutputStream(OUT));

        if(false){
            generateCase();
            return;
        }

        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            out.print("Case #" + i + ":");
            int N = sc.nextInt();
            int M =sc.nextInt();
            sc.nextLine();

            for (int j = 0; j < N; j++) {
                dict[j]=sc.nextLine().trim().toLowerCase();
            }

            String[] L =  new String[M];

            for (int j = 0; j < M; j++) {
                L[j]=sc.nextLine().trim().toLowerCase();
            }
            String[] rr = solve(N, M, L);
            for (int j = 0; j < rr.length; j++) {
                out.print(" " + rr[j]);
            }
            out.println();
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new Solution().run();
    }
}
