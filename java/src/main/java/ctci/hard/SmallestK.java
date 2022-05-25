package ctci.hard;
import java.util.*;
public class SmallestK {

    public int findKSmallest(int[] arr, int k){

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());


        for(int i=0; i<arr.length; ++i){
            pq.offer(arr[i]);
            if(pq.size() > k)
                pq.poll();
        }

        return pq.peek();
    }


    public static void main(String[] args) {
        int[] data =new int[]{1,4,5,6,3,8,9,0,1,3,4,5,2,2};

        SmallestK test = new SmallestK();

        int[] sortData = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            sortData[i] = data[i];
        }
        Arrays.sort(sortData);
        Arrays.stream(sortData).forEach(x -> System.out.printf("%d ", x));
        System.out.println();
        System.out.println(test.findKSmallest(data, 3));
        System.out.println(test.findKSmallest(data, 7));
        System.out.println(test.findKSmallest(data, 12));
    }
}
