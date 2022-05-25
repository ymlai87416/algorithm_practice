package ctci.hard;
import java.util.*;

public class MissingNumber {
    int[] arr;

    public int missingNumber(int n){
        List<Integer> curIndex = new ArrayList<>();
        List<Integer> index1;
        List<Integer> index0;

        for(int i=0; i<n; ++i)
            curIndex.add(i);

        int ptr = 0;
        int res = 0;
        while(curIndex.size()> 1){
            int expectSize1 = (n+1)/2;
            int expectSize0 = n/2 == 0 ? n/2 : n/2+1;
            index0 = new ArrayList<>();
            index1 = new ArrayList<>();

            for(Integer i: curIndex){
                if(fetchBit(i, ptr) == 0)
                    index0.add(i);
                else
                    index1.add(i);
            }

//now check
            if(index1.size() == expectSize1){
                curIndex = index0;
            }
            else{
                curIndex = index1;
                res = res | (1 << ptr);
            }
            ptr++;
            n = n/2;
        }

        return res;
    }

    private int fetchBit(int i, int j){
        int x = arr[i] & (1 << j);
        return x == 0 ? 0: 1;
    }

    public static void main(String[] args) {
        MissingNumber test = new MissingNumber();
        test.arr = new int[]{10, 8, 9, 1,6,4, 5, 0, 2,7};
        System.out.println(test.missingNumber(10));
    }
}
