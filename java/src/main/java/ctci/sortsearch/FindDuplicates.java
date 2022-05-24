package ctci.sortsearch;

import java.util.*;

public class FindDuplicates {


    public void findDuplicates(int[] data){
        int L = 32000;
        BitSet bs = new BitSet(L);

        for(int i=0; i<data.length; ++i){
            if(bs.get(data[i]))
                System.out.println(data[i]);
            else
                bs.set(data[i]);
        }
    }

    public static void main(String[] args) {
        FindDuplicates test=  new FindDuplicates();
        int[] cd = createDuplicate();
        test.findDuplicates(cd);
    }


    private static int[] createDuplicate(){
        int cnt=1;
        int[] data = new int[32000];
        Random r = new Random();

        while(cnt< 32000){
            if(r.nextInt(100) > 85)
                data[cnt] = r.nextInt(cnt);
            else
                data[cnt] = cnt;
            ++cnt;
        }

        return data;
    }
}
