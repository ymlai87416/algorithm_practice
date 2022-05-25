package ctci.hard;

public class VolumeOfHistogram {

    public int volumeOfHistogram(int[] arr){


        int left = 0;
        int right = arr.length-1;

        int curH = 0;
        int res = 0;
        while(left < right){
            if(arr[left] < arr[right]){
                res += curH - arr[left];
                left++;
            }
            else{
                res += curH - arr[right];
                --right;
            }

            curH = Math.max(curH, Math.min(left, right));
        }

        return res;
    }


    public static void main(String[] args) {
        int[] data = new int[]{0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 5, 0, 1, 0, 0, 0};
        VolumeOfHistogram test = new VolumeOfHistogram();
        System.out.println(test.volumeOfHistogram(data));
    }
}
