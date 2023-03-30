package Leetcode.BiWeek82;
import java.util.*;

class C {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        int sum = 0;
        for(int i=0; i<n; ++i){
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
        }

        Arrays.sort(diff);
        int totalC = k1+k2;

        int target = 0;
        int scnt = -1;
        while(true){
            int cscnt = 0;
            sum = 0;
            for(int i=0; i<n; ++i)
                if(target <= diff[i]){
                    sum += diff[i];
                    cscnt++;
                }
            System.out.println("sum: "+sum );
            target = Math.max(0, sum-totalC) / cscnt;
            if( (sum-totalC) %n > 0)
                target+=1;

            if(cscnt == scnt){
                System.out.println("target converge: "+target + " " + scnt + " " + cscnt);
                break;
            }
            else{

                System.out.println("target X: "+target + " " + scnt + " " + cscnt);
                scnt = cscnt;
            }
        }
        System.out.println("target: " + target);


        long dsum = 0;
        for(int i=n-1; i>=0; --i){
            int from = diff[i];
            if(diff[i] > target && totalC > 0){
                if(diff[i] - totalC < target){

                    totalC -= (diff[i] - target);
                    diff[i] = target;
                }
                else{
                    diff[i] -= totalC;
                    totalC = 0;
                }


            }
            System.out.println(i + ": " + from + " " + diff[i]);

        }

        //now should be all the same, remove if possibl
        for(int i=n-1; i>=0; --i){
            if(diff[i] > 0 && totalC > 0){
                diff[i] -=1;
                totalC--;
            }

            dsum += diff[i] * diff[i];
        }

        return dsum;

        /*
        PriorityQueue<Integer> tmp = new PriorityQueue<>(Collections.reverseOrder());
        int minH = Integer.MAX_VALUE;
        int maxH = Integer.MIN_VALUE;
        int[] diff = new int[n];

        int low = minH;
        int high = maxH;

        int mid = 0;
        //find the water level, which able to fill to that level
        while(low < high){
            //fill the k1+k2 stuff in the array
            mid = low+(high-low)/2;

            int tc = 0;
            for(int i=0; i<n; ++i){
                if(mid > diff[i])
                    tc+= mid - diff[i];
            }

            if(tc > totalC)
                high = mid-1;
            else
                low = mid;
        }

        //now we get low
        for(int i=0; i<n; ++i){


        }*/

    }
}