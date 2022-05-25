package ctci.moderate;
import java.util.*;

public class SumSwap {
    public int[] sumSwap(int[] list1, int[] list2){

        int sum1 = 0;
        int sum2 = 0;
        HashSet<Integer> set2 = new HashSet<Integer>();
        for(int i=0; i<list1.length; ++i){
            sum1 += list1[i];
        }

        for(int i=0; i<list2.length; ++i){
            set2.add(list2[i]);
            sum2 += list2[i];
        }

        int diff = sum1-sum2;
        if(diff % 2 == 0) return null; //This cannot be done if all are integers.

        for(int i=0; i<list1.length; ++i){
            int v = list1[i];
            int v2 = list1[i] + (diff/2);
            if(set2.contains(v2))
                return new int[]{v, v2};
        }

        return null;

    }


    public static void main(String[] args) {
        int[] d1 = new int[]{4, 1, 2, 1, 1, 2};
        int[] d2 = new int[]{3, 6, 3, 3};

        SumSwap test = new SumSwap();
        var r = test.sumSwap(d1, d2);
        if(r!=null)
            System.out.printf("%d, %d\n", r[0], r[1]);
        else
            System.out.println("No answer.");
    }
}
