package ctci.recursion;
import java.util.*;

public class PermutationsWithoutDups {
    char[] data;
    List<String> result;

    public List<String> permutationsWithoutDups(String a){
        result = new ArrayList<String>();
        data = new char[a.length()];
        for(int i=0; i<a.length(); ++i)
            data[i] = a.charAt(i);

        helper(0);
        return result;
    }

    private void helper(int index){
        if(index == data.length)
            result.add(new String(data));
        for(int i=index; i<data.length; ++i){
            swap(index, i);
            helper(index+1);
            swap(index, i);
        }

    }

    private void swap(int a, int b){
        char t = data[a];
        data[a] = data[b];
        data[b] = t;
    }


    public static void main(String[] args) {
        PermutationsWithoutDups test = new PermutationsWithoutDups();
        var result = test.permutationsWithoutDups("abc");

        if(haveDup(result)) {
            System.out.println("Have duplicate.");
            return;
        }

        System.out.println("size: "+ result.size());
        result.forEach(x -> System.out.println(x));
    }

    private static boolean haveDup(List<String> data){
        Collections.sort(data);
        for(int i=1; i<data.size(); ++i){
            if(data.get(i).compareTo(data.get(0)) == 0)
                return true;
        }
        return false;
    }
}
