package Leetcode.AmazonMock;

public class OnsiteQ1 {

    public int[] replaceElements(int[] arr) {
        int rMax = -1;
        int[] result = new int[arr.length];
        for (int i = arr.length-1; i >=0 ; i--) {
            result[i] = rMax;
            if(result[i] > rMax)
                rMax = result[i];
        }

        return result;
    }


    public static void main(String[] args){

    }
}
