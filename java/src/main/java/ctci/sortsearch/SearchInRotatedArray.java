package ctci.sortsearch;
import java.util.*;

public class SearchInRotatedArray {
    public int searchInRotatedArray(int[] arr, int target){

        int low = 0;
        int high = arr.length-1;
        int mid;

        while(low < high){
            mid = low + (high-low)/2;  //low -> high-1;
            int val = arr[mid];

            if(arr[mid] > arr[high]){
                low = mid+1;
            }
            else{
                high = mid;
            }
        }

        //the pivot is at low
        int left = Arrays.binarySearch(arr, 0, low, target);
        if(left >= 0) return left;
        int right = Arrays.binarySearch(arr, low, arr.length, target);
        if(right >= 0) return right;

        return -1; //not found
    }


    public static void main(String[] args) {
        SearchInRotatedArray test = new SearchInRotatedArray();
        int[] data = new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5,7, 10, 14};
        System.out.println(test.searchInRotatedArray(data, 5 ));
    }
}
