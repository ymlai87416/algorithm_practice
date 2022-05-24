package ctci.sortsearch;

public class SortedSearchNoSize {

    public int sortedSearchNoSize(Listy list, int target){
        int low = 0;
        int high = 0;

        while(low == high){
            high = high == 0? 1: high * 2;
            int val = list.elementAt(high);
            if(val != -1 && val < target){
                low = high;
            }
        }

        //now we have a valid range

        while(low <= high){
            int mid = low+(high-low)/2;
            int val = list.elementAt(mid);
            if(val == target)
                return mid;
            if(val == -1 || val > target)
                high = mid-1;
            else
                low=mid+1;
        }

        return -1;
    }




    public static void main(String[] args) {
        SortedSearchNoSize test = new SortedSearchNoSize();
        int[] data = new int[]{1, 4,5, 8, 12,34, 80, 81,82,90,102};
        Listy list = new Listy(data);
        System.out.println(test.sortedSearchNoSize(list, 10));
        System.out.println(test.sortedSearchNoSize(list, 102));
    }
}

class Listy{
    int[] arr;

    public Listy(int[] arr){
        this.arr = arr;
    }

    public int elementAt(int i){
        if(i < arr.length)
            return arr[i];
        else
            return -1;
    }
}