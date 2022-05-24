package ctci.moderate;
import java.util.*;
public class SmallestDifference {

    public int smallestDifference(int[] arr1, int[] arr2){
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int diff = Integer.MAX_VALUE;

        int ptr1 =0;
        int ptr2 = 0;

        while(ptr1 < arr1.length && ptr2 < arr2.length){
            int cdiff = Math.abs(arr1[ptr1] - arr2[ptr2]);
            diff = Math.min(diff, cdiff);

            if(arr1[ptr1] < arr2[ptr2])
                ptr1++;
            else
                ptr2++;
        }

        return diff;
    }

    public static void main(String[] args) {
        SmallestDifference test = new SmallestDifference();

        int[] arr1 = new int[]{1, 3, 15, 11, 2};
        int[] arr2 = new int[]{23, 127,235, 19, 8};

        System.out.println(test.smallestDifference(arr1, arr2));
    }
}
