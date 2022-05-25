package ctci.hard;

public class MajorityElement {

    public int majorityElement(int[] arr){
        int cur = arr[0];
        int sum = 1;
        for(int i=1; i<arr.length; ++i){
            if(arr[i] != cur){
                --sum;
                if(sum == 0){
                    cur =arr[i]; sum=1;
                }
            }
            else
                sum++;
        }

        int cnt = 0;
        for(int i=0; i<arr.length; ++i){
            if(arr[i] == cur)++cnt;
        }

        return cnt >= arr.length/2 ? cur: -1;
    }

    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 5, 9, 5, 9, 5, 5, 5};
        int[] data2 = new int[]{1,2,3,4,5,6,7,8,9,10};
        MajorityElement test = new MajorityElement();
        System.out.println(test.majorityElement(data));
        System.out.println(test.majorityElement(data2));
    }
}
