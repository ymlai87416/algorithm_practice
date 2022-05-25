package ctci.hard;

import java.util.Random;

public class RandomSet {

    public int[] randomSet(int[] arr, int m){
        int n = arr.length;
        Random r = new Random();

        for(int i=n-1, j=m; j>=0; --i, --j){
            int nr = r.nextInt(i+1);
            swap(arr, nr, i);
        }

        int[] result = new int[m];
        for(int i=0; i<m; ++i){
            result[i] = arr[n-i-1];
        }

        return result;
    }

    private void swap(int[] arr, int p1, int p2){
        int t = arr[p1];
        arr[p1] = arr[p2];
        arr[p2]  =t;
    }


    public static void main(String[] args) {
        int[] data = new int[]{1,2,3,4,5,6,7,8,9, 10};
        RandomSet test = new RandomSet();
        for (int i = 0; i < 10; i++) {
            var rr = test.randomSet(data, 6);
            for (int j = 0; j < 6; j++) {
                System.out.printf("%2d ", rr[j]);
            }
            System.out.println();
        }
    }
}
