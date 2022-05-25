package ctci.moderate;
import java.util.*;

public class T9 {

    HashMap<String, List<String>> lookup;
    public void t9Preprocess(String[] words){
        lookup=  new HashMap<>();

        for(int i=0; i<words.length; ++i){
            String numRep = toNumRepresentation(words[i]);
            if(!lookup.containsKey(numRep))
                lookup.put(numRep, new ArrayList<String>());
            lookup.get(numRep).add(words[i]);
        }

    }

    public List<String> t9(String[] words, String key){
        t9Preprocess(words);
        return lookup.get(key);
    }

    int[] mapInt = new int[]{2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,8,8,8,9,9,9,9};

    private String toNumRepresentation(String s){
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<s.length(); ++i){
            char c = s.charAt(i);
            int ci = c-'a';
            sb.append(mapInt[ci]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        T9 test = new T9();
        String[] words=  new String[]{"instinct",
                "creation",
                "vision",
                "afford",
                "descent",
                "eliminate",
                "catalogue",
                "fragrant",
                "indication",
                "basis",
                "mass",
                "oven",
                "activate",
                "wedding",
                "document",
                "wrong",
                "occupy",
                "fragment",
                "bark",
                "stun",
                "paralyzed",
                "transition",
                "miscarriage",
                "camp",
                "drawer",
                "lock",
                "export",
                "bucket",
                "printer",
                "hope",
                "chimney",
                "fox",
                "beg",
                "demonstrate",
                "quit",
                "used",
                "available",
                "mixture",
                "program",
                "withdraw",
                "translate",
                "sickness",
                "stamp",
                "homosexual",
                "pupil",
                "kit",
                "bleed",
                "death",
                "state",
                "century",
                "recycle",
                "tree"};

        var result = test.t9(words, "8733");
        System.out.println(result);
    }

}
