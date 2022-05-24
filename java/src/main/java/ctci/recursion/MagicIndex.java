package ctci.recursion;

public class MagicIndex {
    public int magicIndex(int[] arr){

        int low=  0;
        int high = arr.length-1;

        while(low <= high){
            int mid = (low+high)/2;

            if(arr[mid] == mid) return mid;

            if(arr[mid] < mid)  // 3 3 3 3 6 and mid=4, arr[mid] =6.
                low = mid+1;
            else
                high = mid-1;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] data = new int[]{-40, -20, -1, 1, 2, 3, 5, 7, 9, 12, 13};

        MagicIndex test = new MagicIndex();
        System.out.println(test.magicIndex(data));
    }
}
