package Leetcode.BloombergMock;

public class OnsiteQ2 {

    public double myPow(double x, int n) {
        if(n < 0){
            long nn = (long)(n) * -1;
            return 1/helper(x, nn);
        }
        else
            return helper(x, n);
    }

    public double helper(double x, long n){
        if(n == 0) return 1;
        if(n == 1) return x;

        double  a = helper(x,n/2);
        if(n%2 == 0)
            return a * a;
        else
            return a * a * x;
    }

    public static void main(String[] args){
        OnsiteQ2 q = new OnsiteQ2();
        q.myPow(2.0, -2147483648);
    }
}
