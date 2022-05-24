package ctci.sortsearch;
import java.util.*;

public class GroupAnagrams {

    void sortAnagram(List<String> input){
        HashMap<String, String> fingerprints = new HashMap<>();

        Comparator<String> comparator = new Comparator<>(){
            public int compare(String a, String b){
                String fp1 = fingerprints.get(a);
                String fp2 = fingerprints.get(b);
                if(fp1 == fp2) return a.compareTo(b);
                return fp1.compareTo(fp2);
            }
        };

        for(int i=0; i<input.size(); ++i){
            String fp = createAnagramFingerprint(input.get(i));
            fingerprints.put(input.get(i), fp);
        }

        Collections.sort(input, comparator);

    }

    String createAnagramFingerprint(String s){
        s  = s.toLowerCase();
        int[] freq = new int[26];

        for(int i=0; i<s.length(); ++i){
            char c = s.charAt(i);
            int ci = (int) (c-'a');
            freq[ci]++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<26; ++i){
            char c =(char)('a'+ i);
            if(freq[i] == 0 ) continue;
            sb.append(""+c+":"+freq[i]);
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        List<String> data = new ArrayList<>(List.of("dog", "meet", "god", "temp", "teem"));
        GroupAnagrams test = new GroupAnagrams();
        test.sortAnagram(data);
        System.out.println(data);
    }
}
