package ctci.hard;

public class MissingTwo {

    public int[] missingTwo(int[] arr){

        int[] result = new int[2];
        int ptr = 0;
        int N = arr.length+2;
        boolean hasN_1 = false;
        boolean hasN = false;

        for(int i=0; i<arr.length; ++i){
            int x = Math.abs(arr[i]);
            if(x == N-1) hasN_1 = true;
            else if(x == N) hasN = true;
            else arr[x-1] = -arr[x-1];
        }

        for(int i=0; i<arr.length; ++i){
            if(arr[i] > 0)
                result[ptr++] = i+1;
        }
        if(!hasN_1) result[ptr++] = N-1;
        if(!hasN) result[ptr++] = N;

        return result;
    }


    public static void main(String[] args) {

    }
}
