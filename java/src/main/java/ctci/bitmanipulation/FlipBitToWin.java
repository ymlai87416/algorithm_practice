package ctci.bitmanipulation;

public class FlipBitToWin {
    public int flipBinToWin(int N){
        int f0 =0;
        int f1 = 0;
        int r = 0;
        int n = N;

        while(n != 0){
            int c = n & 1;

            if(c == 0){
                f1 = f0+1;
                f0 = 0;
                if(f1 > r) r = f1;
            }
            else{
                f0++;
                f1++;
                if(f0 > r) r = f0;
                if(f1 > r) r = f1;
            }

            n=n >> 1;
        }
        //we can flip 1 beyond 32 bit?
        f1 = f0+1;
        if(r < f1) r = f1;

        return r;
    }


    public static void main(String[] args) {
        FlipBitToWin test = new FlipBitToWin();
        System.out.println( test.flipBinToWin(1775));
    }
}
