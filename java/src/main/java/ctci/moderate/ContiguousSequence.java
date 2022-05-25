package ctci.moderate;

public class ContiguousSequence {

    public int contiguousSequence(int[] arr){

        int r = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0; i<arr.length; ++i){
            sum = Math.max(sum+arr[i], arr[i]);
            if(sum > r) r = sum;
        }

        return r;
    }


    public static void main(String[] args) {
        int[] data = new int[]{2, -8, 3, -2, 4, -10};
        ContiguousSequence test = new ContiguousSequence();
        System.out.println(test.contiguousSequence(data));
    }
}
