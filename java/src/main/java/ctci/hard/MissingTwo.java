package ctci.hard;

public class MissingTwo {

    public int[] missingTwoX(int[] arr){

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

    public int[] missingTwo(int[] arr){
        int N = arr.length+2;
        long sum = 0;
        long sumSq = 0;

        for(int i=0; i<arr.length; ++i){
            sum += arr[i];
            sumSq += arr[i] * arr[i];
        }

        long sum1 = 0;
        long sumSq1 = 0;
        for(int i=1; i<=N; ++i){
            sum1 += i;
            sumSq1 += i*i;
        }

        long xyS = sum1-sum;
        long x2y2S = sumSq1 - sumSq;
        long xy = (xyS*xyS - x2y2S )/2;
        //now we have xy and x+y => x(xyS-x) = xy
        long x = (long)(xyS + Math.sqrt(xyS*xyS - 4* xy))/2;
        long y = (long)(xyS - Math.sqrt(xyS*xyS - 4* xy))/2;

        return new int[]{(int) x, (int) y};
    }



    public static void main(String[] args) {
        int[] data = new int[]{1,2,3,5,6,8,9,10};
        MissingTwo test = new MissingTwo();
        int[] r = test.missingTwo(data);
        System.out.printf("%d %d", r[0], r[1]);
    }
}
