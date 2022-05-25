package ctci.moderate;

public class SubSort {

    public int[] subSort(int[] arr){
        int left =0; int right = arr.length-1;
        //find the left unsorted
        int max = arr[0];
        int leftFirstUnsorted = Integer.MAX_VALUE;
        for(int i=1; i<arr.length; ++i){
            max = Math.max(max, arr[i]);
            if(arr[i] < max && arr[i] < leftFirstUnsorted)
                leftFirstUnsorted = arr[i];
        }

        //find the right unsorted
        int min = arr[arr.length-1];
        int rightFirstUnsorted = Integer.MIN_VALUE;
        for(int i=arr.length-2; i>=0; --i){
            min = Math.min(min, arr[i]);
            if(arr[i] > min && arr[i] > rightFirstUnsorted)
                rightFirstUnsorted = arr[i];
        }

        if(leftFirstUnsorted == Integer.MAX_VALUE)
            return null;  //the list is in order

        for(int i=0; i<arr.length; ++i){
            if(arr[i] > leftFirstUnsorted){
                left = i;
                break;
            }
        }

        for(int i=arr.length-1; i>=0; --i){
            if(arr[i] < rightFirstUnsorted){
                right = i;
                break;
            }
        }


        return new int[] {left, right};
    }


    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        SubSort test = new SubSort();
        int[] r = test.subSort(data);

        System.out.printf("(%d, %d)\n", r[0], r[1]);

        data = new int[]{1,2,3,4,5,6,7,8,9,10};
        r = test.subSort(data);

        System.out.printf("result should be null: in order " + r);
    }
}
