package ctci.sortsearch;
import java.util.*;

public class PeaksAndValleys {

    //O(nlog(n))
    public int[] peaksAndValleys(int[] arr){
        Arrays.sort(arr);
        int n = arr.length;
        int[] result = new int[arr.length];
        //from small => large,
        int cnt= 0;
        for(int i=0; i<n; i+=2){
            result[i] = arr[cnt++];
        }
        for(int i=1; i<n; i+=2){
            result[i] = arr[cnt++];
        }

        return result;

    }


    public static void main(String[] args) {
        int[] data=  new int[]{5, 3, 1, 2, 3};
        PeaksAndValleys test  = new PeaksAndValleys();
        int[] result = test.peaksAndValleys(data);
        for (int i = 0; i < data.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
