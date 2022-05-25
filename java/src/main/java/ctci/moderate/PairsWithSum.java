package ctci.moderate;
import java.util.*;

public class PairsWithSum {
    public List<int[]> pairsWithSum(int[] arr, int K){

//assume we want to find the locations.

        HashMap<Integer, List<Integer>> lookup = new HashMap<>();

        List<int[]> result = new ArrayList<>();
        for(int i=0; i<arr.length; ++i){
            int v = arr[i];
            int v2 = v - arr[i];

            if(lookup.containsKey(v2)){
                List<Integer> v2List = lookup.get(v2);
                for(int j=0; j<v2List.size(); ++i)
                    result.add(new int[]{i, v2List.get(j) });
            }

            if(!lookup.containsKey(arr[i]))
                lookup.put(arr[i], new ArrayList<Integer>());
            lookup.get(arr[i]).add(i);
        }

        return result;
    }


    public static void main(String[] args) {

    }
}
