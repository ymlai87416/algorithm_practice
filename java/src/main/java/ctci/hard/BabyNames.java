package ctci.hard;
import java.util.*;

public class BabyNames {
    public HashMap<String, Integer> countRealName(HashMap<String, Integer> frequency,
                                                  String[][] pairs){

        HashMap<String, Integer> result = new HashMap<>();
        HashMap<String, Integer> index = new HashMap<>();

        int cnt = 0;
        UnionFind uf = new UnionFind(frequency.size());

        for(String name: frequency.keySet()){
            index.put(name, cnt);
            uf.setSize(cnt, frequency.get(name));
            ++cnt;
        }

        for(int i=0; i<pairs.length; ++i){
            String sa = pairs[i][0];
            String sb = pairs[i][1];

            int ia = index.getOrDefault(sa, -1);
            int ib = index.getOrDefault(sb, -1);
            if(ia == -1 || ib == -1) continue;
            uf.unionSet(ia, ib);
        }

        for(String name: frequency.keySet()){
            int i = index.get(name);
            if(uf.findSet(i) == i){
                result.put(name, uf.getSize(i));
            }
        }
        return result;
    }


    public static void main(String[] args) {
        HashMap<String, Integer> frequency
                = new HashMap<>();
        frequency.put("John", 15);
        frequency.put("Jon", 12);
        frequency.put("Chris", 13);
        frequency.put("Kris", 4);
        frequency.put("Christopher", 19);

        String[][] pairs = new String[][]{
                {"Jon", "John"}, {"John", "Johnny"}, {"Chris", "Kris"}, {"Chris", "Christopher"}
        };

        BabyNames test = new BabyNames();
        var result = test.countRealName(frequency, pairs);
        for(String x: result.keySet())
            System.out.println(x + ": " + result.get(x));
    }
}
